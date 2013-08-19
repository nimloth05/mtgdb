package org.mtgdb.grabber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Sandro Orlando
 */
public final class GrabberText {

  private final String url;

  public GrabberText(final String url) {
    this.url = url;
  }

  public static void grabAll(final String url) {
    final GrabberText grabberText = new GrabberText(url);
    Thread thread = new Thread(new Runnable() {
          public void run() {
            try {
              grabberText.grab();
            } catch (IOException e) {

            }
          }
        }, "grabberText");
    thread.start();
  }

  private void grab() throws IOException {
    URL urlObject = new URL(url);
    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlObject.openConnection().getInputStream(), "utf8"));
    String line;
    StringBuilder builder = new StringBuilder();
    while ( (line = bufferedReader.readLine()) != null) {
      builder.append(line);
    }
    handleContent(builder);
  }

  private void handleContent(final StringBuilder builder) {

  }

}
