package org.mtgdb.ui.main.action;

import com.google.inject.Inject;
import org.mtgdb.db.repository.IContainerRepository;
import org.mtgdb.db.repository.IEditionRepository;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.db.repository.IPhysicalCardRepository;
import org.mtgdb.model.CardCondition;
import org.mtgdb.model.Container;
import org.mtgdb.model.Edition;
import org.mtgdb.model.PhysicalCard;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.dialog.properties.PropertyFactory;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.dialog.properties.selection.EnumModelBody;
import org.mtgdb.ui.util.dialog.properties.selection.ISelectionPropertyModelBody;
import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.ui.PropertiesDialog;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public final class AddPhysicalCardAction extends AbstractAction {

  private final IEditionRepository editionRepository;
  private final IPhysicalCardRepository physicalCardRepository;
  private final IContainerRepository containerRepository;
  private final IMagicCardRepository magicCardRepository;
  private boolean showDialogAgain = false;

  @Inject
  public AddPhysicalCardAction(final IEditionRepository editionRepository, final IPhysicalCardRepository physicalCardRepository, final IContainerRepository containerRepository, final IMagicCardRepository magicCardRepository) {
    super();
    this.editionRepository = editionRepository;
    this.physicalCardRepository = physicalCardRepository;
    this.containerRepository = containerRepository;
    this.magicCardRepository = magicCardRepository;
    putValue(Action.NAME, "Add Card");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    PhysicalCardProperties properties = new PhysicalCardProperties();
    properties.condition = CardCondition.mint;
    properties.cardNumber = "1";
    properties.container = containerRepository.getAll().get(0);
    properties.language = "English";
    //TODO: GetDefaultedition or something - this will crash if there are no edition in the db!
    properties.edition = editionRepository.getAll().get(0);
    showDialogAgain = true;
    while (showDialogAgain) {
      createPropertiesGroup(properties);
    }
  }

  private void createPropertiesGroup(final PhysicalCardProperties properties) {
    PropertyGroup propertyGroup = new PropertyGroup("physicalCardProperties");
    propertyGroup.setOkRunnable(createOkRunnable(properties));
    propertyGroup.setCancelRunnable(createCancelRunnable());
    PropertyFactory factory = new PropertyFactory();
    propertyGroup.add(factory.selectionProperty("edition", createEditionSelectionBody(properties)))
      .add(factory.stringProperty("cardNumber", createNumberBody(properties)))
      .add(factory.selectionProperty("condition", createConditionBody(properties)))
      .add(factory.stringProperty("language", createLanguageBody(properties)))
      .add(factory.selectionProperty("container", createContainerSelectionBody(properties)));
    PropertiesDialog dialog = new PropertiesDialog(FrameFactory.getMainFrame(), propertyGroup);
    dialog.show();
  }

  private Runnable createCancelRunnable() {
    return new Runnable() {
      @Override
      public void run() {
        showDialogAgain = false;
      }
    };
  }

  private ISelectionPropertyModelBody<?> createContainerSelectionBody(final PhysicalCardProperties properties) {
    return new ISelectionPropertyModelBody<Container>() {
      @Override
      public ListModel initialize(final IPropertyModelContext context) {
        DefaultListModel<Container> containerModel = new DefaultListModel<>();
        Collection<Container> containers = containerRepository.getAll();
        for (Container container : containers) {
          containerModel.addElement(container);
        }
        return containerModel;
      }

      @Override
      public Object getInitialSelectedElement() {
        return properties.container;
      }

      @Override
      public void ok(final Container object) {
        properties.container = object;
      }
    };
  }

  private IStringModelBody createLanguageBody(final PhysicalCardProperties properties) {
    return new IStringModelBody() {
      @Override
      public Document initialize(final IPropertyModelContext context) {
        PlainDocument document = new PlainDocument();
        DocumentHelper.setText(document, properties.language);
        return document;
      }

      @Override
      public void ok(final Document document) {
        properties.language = DocumentHelper.getText(document);
      }
    };
  }

  private ISelectionPropertyModelBody<?> createConditionBody(final PhysicalCardProperties properties) {
    return new EnumModelBody<CardCondition>(CardCondition.class) {
      @Override
      public Object getInitialSelectedElement() {
        return properties.condition;
      }

      @Override
      public void ok(final CardCondition object) {
        properties.condition = object;
      }
    };
  }

  private Runnable createOkRunnable(final PhysicalCardProperties properties) {
    return new Runnable() {
      @Override
      public void run() {
        PhysicalCard card = new PhysicalCard();
        card.setCard(magicCardRepository.getCard(properties.edition, properties.cardNumber));
        card.setCondition(properties.condition);
        card.setContainer(properties.container);
        card.setCondition(properties.condition);
        card.setLanguage(properties.language);
        physicalCardRepository.save(card);
        showDialogAgain = true;
      }
    };
  }

  private IStringModelBody createNumberBody(final PhysicalCardProperties properties) {
    return new IStringModelBody() {
      @Override
      public Document initialize(final IPropertyModelContext context) {
        PlainDocument document = new PlainDocument();
        DocumentHelper.setText(document, properties.cardNumber);
        return document;
      }

      @Override
      public void ok(final Document document) {
        properties.cardNumber = DocumentHelper.getText(document);
      }
    };
  }

  private ISelectionPropertyModelBody<?> createEditionSelectionBody(final PhysicalCardProperties physicalCardProperties) {
    return new ISelectionPropertyModelBody<Edition>() {

      @Override
      public ListModel initialize(final IPropertyModelContext context) {
        Collection<Edition> editions = editionRepository.getAll();
        DefaultListModel<Edition> model = new DefaultListModel<>();
        for (Edition edition : editions) {
          model.addElement(edition);
        }
        return model;
      }

      @Override
      public Object getInitialSelectedElement() {
        return physicalCardProperties.edition;
      }

      @Override
      public void ok(final Edition object) {
        physicalCardProperties.edition = object;
      }
    };
  }

  private static class PhysicalCardProperties {

    private Edition edition;
    private String cardNumber;
    private CardCondition condition;
    private String language;
    private Container container;
  }
}
