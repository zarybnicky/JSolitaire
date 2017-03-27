/*
 * 
 */
package jgraphics;

import java.awt.Component;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import jsolitaire.Card;

public class CardRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setBackground(list.getSelectionBackground());

        Card card;
        card = (Card) value;
        setIcon(card.getIcon());
        if (getIcon() != null) {
            if (index != list.getModel().getSize() - 1) {
                setIcon(new ImageIcon(createImage(new FilteredImageSource(((ImageIcon) getIcon()).getImage().getSource(),
                        new CropImageFilter(0, 0, getIcon().getIconWidth(), 20)))));
            }
            setFont(list.getFont());
        } else {
            ImageIcon icon = new ImageIcon("../lib/missing.gif");
            setIcon(icon);
        }
        return this;
    }
}
