package org.mtgdb.ui.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Date: 27.12.10
 * Time: 00:04
 *
 * @author Sandro Orlando
 */
public final class ImageLoader {

  private ImageLoader() {}

  public static Icon loadAsIcon(final String iconName) {
    return new ImageIcon(load(iconName));
  }

  public static BufferedImage load(final String name) {
    URL url = ImageLoader.class.getResource(name);
    BufferedImage image;
    try {
      image = ImageIO.read(url);
    }
    catch (Exception e) {
      image = ImageUtil.createErrorImage(16, 16);
    }

    return image;
  }

}
