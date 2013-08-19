package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class GrabberJsoup {

  private final String url;

  public GrabberJsoup(final String url) {
    this.url = url;
  }

  public static void grabAll(final String url) {
    final GrabberJsoup grabberText = new GrabberJsoup(url);
    Thread thread = new Thread(new Runnable() {
      public void run() {
        try {
          grabberText.grab(new IProgressMonitor() {
            @Override
            public void setMessage(final String message) {
            }

            @Override
            public void step(final int step) {
            }
          });
        } catch (IOException e) {

        }
      }
    }, "grabberText");
    thread.start();
  }

  public void grab(final IProgressMonitor monitor) throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements tables = doc.select("table .even");
    int counter = -1;
    for (Element row : tables) {
      final Elements td = row.select("td");
      String label = td.get(0).text();

      monitor.setMessage("Fetching card " + label);
      monitor.step(++counter);

      System.out.println("Card: " + td.get(0).text() + " " + td.get(1).text() + " url: " + td.get(1).child(0).attr("href"));
    }
    System.out.println(tables);
  }

}
