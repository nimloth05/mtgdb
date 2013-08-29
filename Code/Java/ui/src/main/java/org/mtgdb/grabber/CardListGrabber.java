package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.util.assertion.Assert;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public final class CardListGrabber implements IEditionGrabberListener {

  private final ICardListGrabberListener listener;

  public CardListGrabber(final ICardListGrabberListener listener) {
    this.listener = listener;
  }

  public void grab(final String url, final String editionId) {
    try {
      grabEdition(url, editionId, listener);
    } catch (IOException e) {
      Assert.log(e);
    }
  }

  public void grabEdition(final String editionUrl, final String editionShort, final ICardListGrabberListener listener) throws IOException {
    Document doc = Jsoup.connect(editionUrl).get();
    Elements even = doc.select("table .even");
    Elements odd = doc.select("table .odd");

   listener.begin(doc.title(), editionShort, (even.size() + odd.size()));

    grabEditionCards(listener, even);
    grabEditionCards(listener, odd);

    listener.end();
  }

  private void grabEditionCards(final ICardListGrabberListener listener, final Collection<Element> tables) throws IOException {
    for (Element row : tables) {
      final Elements td = row.select("td");
      final String text = td.get(1).text();

      listener.grabCard(Grabber.urlPrefix + td.get(1).child(0).attr("href"), td.get(4).text(), text);
    }
  }

}
