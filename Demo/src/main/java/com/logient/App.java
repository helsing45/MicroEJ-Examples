/**
 *
 */
package com.logient;

import com.logient.material.Colors;
import com.logient.material.Fonts;
import com.logient.material.MaterialTheme;
import com.logient.widget.navigationdrawer.DrawerItem;

import ej.microui.display.Font;
import ej.mwt.style.EditableStyle;
import ej.mwt.style.background.NoBackground;
import ej.mwt.style.background.RectangularBackground;
import ej.mwt.style.dimension.OptimalDimension;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;
import ej.mwt.stylesheet.selector.TypeSelector;
import ej.mwt.util.Alignment;

/**
 *
 */
public class App {
	private static App instance;
	private final MaterialTheme theme;

	private App(MaterialTheme theme) {
		this.theme = theme;
	}

	private static App getInstance() {
		if (instance == null) {
			Colors colors = getAppColors();
			Fonts fonts = getAppFonts();
			instance = new App(new MaterialTheme(colors, fonts, getAppStylesheet(colors, fonts)));
		}
		return instance;
	}

	private static Fonts getAppFonts() {
		return new Fonts(Font.getFont("/fonts/kanit-regular-48.ejf"), // h1
				Font.getFont("/fonts/kanit-regular-36.ejf"), // h2
				Font.getFont("/fonts/kanit-regular-24.ejf"), // h3
				Font.getFont("/fonts/kanit-regular-18.ejf"), // h4
				Font.getFont("/fonts/kanit-medium-24.ejf"), // h5
				Font.getFont("/fonts/kanit-light-16pt.ejf"), // body1
				Font.getFont("/fonts/kanit-light-14pt.ejf"), // body2
				Font.getFont("/fonts/kanit-regular-12pt.ejf") // subtitle
		);
	}

	private static Colors getAppColors() {
		return new Colors(0x6200EE, // primary
				0xFFFFFF, // onPrimary
				0x3700B3, // primaryVariant
				0x03DAC6, // secondary
				0x000000, // onSecondary
				0x018786, // secondaryVariant
				0xFFFFFF, // background
				0x000000, // onBackground
				0xFFFFFF, // surface
				0x000000, // onSurface
				0xB00020, // error
				0xFFFFFF// onError
		);
	}

	private static CascadingStylesheet getAppStylesheet(Colors colors, Fonts fonts) {
		CascadingStylesheet stylesheet = new CascadingStylesheet();

		EditableStyle style = stylesheet.getDefaultStyle();
		style.setColor(colors.onBackground);
		style.setBackground(new RectangularBackground(colors.background));
		style.setHorizontalAlignment(Alignment.HCENTER);
		style.setVerticalAlignment(Alignment.VCENTER);

		// h1 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.h1));
		style.setFont(fonts.h1);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// h2 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.h2));
		style.setFont(fonts.h2);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// h3 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.h3));
		style.setFont(fonts.h3);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// h4 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.h4));
		style.setFont(fonts.h4);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// h5 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.h5));
		style.setFont(fonts.h5);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// body1 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.body1));
		style.setFont(fonts.body1);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// body2 style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.body2));
		style.setFont(fonts.body2);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// subtitle style
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.subtitle));
		style.setFont(fonts.subtitle);
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setVerticalAlignment(Alignment.TOP);

		// Drawer menu
		// int top, int right, int bottom, int left
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.drawerMenu));
		style.setHorizontalAlignment(Alignment.LEFT);
		style.setDimension(OptimalDimension.OPTIMAL_DIMENSION_XY);
		style.setBackground(new RectangularBackground(colors.primary));

		// Drawer content
		// int top, int right, int bottom, int left
		style = stylesheet.getSelectorStyle(new ClassSelector(Styles.drawerContent));
		style.setHorizontalAlignment(Alignment.RIGHT);
		style.setDimension(OptimalDimension.OPTIMAL_DIMENSION_XY);
		style.setBackground(NoBackground.NO_BACKGROUND);

		// drawer item style
		style = stylesheet.getSelectorStyle(new TypeSelector(DrawerItem.class));
		style.setFont(fonts.body1);
		style.setBackground(new RectangularBackground(colors.primary));
		style.setColor(colors.onPrimary);

		return stylesheet;
	}

	public static MaterialTheme getTheme() {
		return getInstance().theme;
	}
}
