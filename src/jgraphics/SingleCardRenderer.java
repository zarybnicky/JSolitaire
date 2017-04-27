package jgraphics;

import java.awt.Component;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import jsolitaire.Card;

/**
 * Renders cards in the foundation
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12).
 */
public class SingleCardRenderer extends JLabel implements ListCellRenderer<Card> {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<? extends Card> list, Card card, int index,
            boolean isSelected, boolean cellHasFocus) {
        
        // Card image to list setup
        setIcon(card.getIcon());
        if (index != list.getModel().getSize() - 1) {
            setIcon(new ImageIcon(createImage(new FilteredImageSource(
                    ((ImageIcon) getIcon()).getImage().getSource(),
                    new CropImageFilter(0, 0, getIcon().getIconWidth(), 0)))));

        }
        return this;
    }
}