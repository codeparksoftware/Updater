package updater;

import javax.swing.UIManager;

public class UIFactory {

	public String getDefaultUI() {
		if (common.OSDetector.isUnix())
			return "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
		else if (common.OSDetector.isWindows())
			return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

		return UIManager.getCrossPlatformLookAndFeelClassName();
	}

}
