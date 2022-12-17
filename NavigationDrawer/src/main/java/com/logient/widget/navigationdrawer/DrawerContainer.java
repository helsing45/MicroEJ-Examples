/**
 *
 */
package com.logient.widget.navigationdrawer;

import com.logient.material.MaterialTheme;
import com.logient.widget.scrollablelist.Scroll;
import com.logient.widget.scrollablelist.ScrollableList;

import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.mwt.Container;
import ej.mwt.Widget;
import ej.mwt.animation.Animation;
import ej.mwt.animation.Animator;
import ej.mwt.util.Size;
import ej.widget.basic.OnClickListener;
import ej.widget.container.LayoutOrientation;

/**
 *
 */
public class DrawerContainer extends Container {
	private final static int EXPAND_WIDTH = 150;
	private final static int RETRACT_WIDTH = 36;
	private final static int ANIMATION_INCREAMENT = 7;

	private final MaterialTheme theme;
	private final Image menuIcon;
	private int drawerWidth = RETRACT_WIDTH;
	private boolean isAnimating = false;
	private boolean expanded = false;
	private final Widget menu;
	private DrawerItem selectedItem;

	public DrawerContainer(MaterialTheme theme, DrawerItem[] items) {
		this.theme = theme;
		this.menuIcon = Image.getImage("/images/ic-list.png");
		this.menu = createMenu(items);
		addChild(this.menu);
		changeDrawerItem(items[0]);
		setEnabled(true);
	}

	protected void changeDrawerItem(DrawerItem item) {
		if (this.selectedItem != null) {
			this.selectedItem.setSelected(false);
			removeChild(this.selectedItem.getDestination());
		}
		this.selectedItem = item;
		this.selectedItem.setSelected(true);
		addChild(this.selectedItem.getDestination());
		requestRender();
	}

	/**
	 * @param createDrawerItems
	 * @return
	 */
	private Widget createMenu(DrawerItem[] createDrawerItems) {
		ScrollableList list = new ScrollableList(LayoutOrientation.VERTICAL);
		for (int i = 0; i < createDrawerItems.length; i++) {
			final DrawerItem item = createDrawerItems[i];
			item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					changeDrawerItem(item);
					requestLayOut();
					retract();
				}

			});
			list.addChild(item);
		}

		Scroll scroll = new Scroll(LayoutOrientation.VERTICAL, false);
		scroll.setChild(list);
		return scroll;
	}

	@Override
	public boolean handleEvent(int event) {
		int type = Event.getType(event);
		if (type == Pointer.EVENT_TYPE) {
			int action = Buttons.getAction(event);
			Pointer pointer = (Pointer) Event.getGenerator(event);
			if (pointer.getY() <= this.menuIcon.getWidth() + 5 && pointer.getX() <= this.menuIcon.getHeight() + 5
					&& action == Buttons.RELEASED) {
				animateSizeChange();
				return true;
			}
		}

		if (this.expanded) {
			if (getMenu().handleEvent(event)) {
				return true;
			}
		} else {
			if (getContent().handleEvent(event)) {
				return true;
			}
		}

		return super.handleEvent(event);
	}

	private void animateSizeChange() {
		if (this.isAnimating) {
			return;
		}

		this.isAnimating = true;
		if (this.expanded) {
			retract();
		} else {
			expand();
		}

	}

	public void expand() {
		Animation expandAnimation = new Animation() {
			@Override
			public boolean tick(long currentTimeMillis) {
				DrawerContainer.this.drawerWidth = Math.min(DrawerContainer.this.drawerWidth + ANIMATION_INCREAMENT,
						EXPAND_WIDTH);
				requestRender();

				if (DrawerContainer.this.drawerWidth == EXPAND_WIDTH) {
					DrawerContainer.this.expanded = true;
					DrawerContainer.this.isAnimating = false;
					getContent().setEnabled(false);
					getMenu().setEnabled(true);
					return false;
				}
				return true;
			}
		};
		Animator animator = new Animator();
		animator.startAnimation(expandAnimation);
	}

	public void retract() {
		Animation expandAnimation = new Animation() {
			@Override
			public boolean tick(long currentTimeMillis) {
				DrawerContainer.this.drawerWidth = Math.max(DrawerContainer.this.drawerWidth - ANIMATION_INCREAMENT,
						RETRACT_WIDTH);
				requestRender();

				if (DrawerContainer.this.drawerWidth == RETRACT_WIDTH) {
					DrawerContainer.this.expanded = false;
					DrawerContainer.this.isAnimating = false;
					getContent().setEnabled(true);
					getMenu().setEnabled(false);
					return false;
				}
				return true;
			}
		};
		Animator animator = new Animator();
		animator.startAnimation(expandAnimation);
	}

	@Override
	protected void layOutChildren(int contentWidth, int contentHeight) {
		// Widget child, int x, int y, int width, int height
		layOutChild(getMenu(), 0, 40, EXPAND_WIDTH, contentHeight - 40);
		layOutChild(getContent(), RETRACT_WIDTH, 0, contentWidth, contentHeight);
	}

	public Widget getMenu() {
		return this.menu;
	}

	public int getMenuContentHeight() {
		return ((Container) ((Scroll) getMenu()).getChild(0)).getContentHeight();
	}

	public Widget getContent() {
		return this.selectedItem.getDestination();
	}

	@Override
	protected void computeContentOptimalSize(Size size) {
		// This create style for the child, the style is important for layOut {TO WRITE IN CONFLUENCE}
		computeChildOptimalSize(getMenu(), this.drawerWidth, NO_CONSTRAINT);
		computeChildOptimalSize(getContent(), size.getWidth() - RETRACT_WIDTH, size.getHeight());
		size.setSize(NO_CONSTRAINT, NO_CONSTRAINT);
	}

	@Override
	protected void renderContent(GraphicsContext g, int contentWidth, int contentHeight) {
		int translateX = g.getTranslationX();
		int translateY = g.getTranslationY();
		int x = g.getClipX();
		int y = g.getClipY();
		int width = g.getClipWidth();
		int height = g.getClipHeight();

		g.setTranslation(RETRACT_WIDTH, y);
		getContent().render(g);

		g.setTranslation(translateX, translateY);
		g.setClip(x, y, width, height);

		if (this.drawerWidth == EXPAND_WIDTH) {
			Painter.drawImage(g, Image.getImage("/images/blur-bg.png"), EXPAND_WIDTH, 0);
		}

		g.setTranslation(translateX, translateY);

		g.setColor(this.theme.colors.primary);
		Painter.fillRectangle(g, 0, 0, this.drawerWidth, contentHeight);
		Painter.drawImage(g, this.menuIcon, 5, 5);

		g.setTranslation(translateX, translateY);

		float drawerExpansionProgress = (this.drawerWidth - RETRACT_WIDTH) / (float) (EXPAND_WIDTH - RETRACT_WIDTH);
		int menuTranslationX = (int) ((EXPAND_WIDTH * drawerExpansionProgress) + -150);
		g.setTranslation(menuTranslationX, 40);
		g.setClip(0, 0, EXPAND_WIDTH, getMenuContentHeight());
		getMenu().render(g);

		g.setTranslation(translateX, translateY);
		g.setClip(x, y, width, height);
	}

}
