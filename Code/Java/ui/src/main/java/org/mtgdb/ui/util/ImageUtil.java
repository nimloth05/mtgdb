package org.mtgdb.ui.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ImageUtil {

  public static final BufferedImage sErrorImage_16 = createErrorImage(16, 16);

  public static final BufferedImage createErrorImage(final int width, final int height) {
    final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
    Graphics2D g = (Graphics2D) image.getGraphics();
    g.setBackground(Color.red);
    g.fillRect(0, 0, width, height);
    g.dispose();
    return image;
  }

  private ImageUtil() {
  }


}
