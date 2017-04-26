/*
 * @authors: Jakub Zarybnický (xzaryb00)
 *           Jiří Záleský (xzales12)
 * VUTBR BIT 2, 2016/17
 *
 * Description: Card renderer for tableaus.
 */

package jgraphics;

import java.awt.Component;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import jsolitaire.Card;

public class CardRenderer extends JLabel implements ListCellRenderer<Card> {

    private static final long serialVersionUID = 1L;
    private int base = 16;

    @Override
    public Component getListCellRendererComponent(JList<? extends Card> list, Card card, int index,
            boolean isSelected, boolean cellHasFocus) {
        
        // Padding setup for variable trimming.
        int padding = this.base;
        int size = list.getModel().getSize();
        if (size - 7 > 0){
            padding = (list.getPreferredSize().height - 57) / size;
        }
        
        // Card image to list setup
        setIcon(card.getIcon());
        if (index != list.getModel().getSize() - 1) {
            setIcon(new ImageIcon(createImage(new FilteredImageSource(
                    ((ImageIcon) getIcon()).getImage().getSource(),
                    new CropImageFilter(0, 0, getIcon().getIconWidth(), padding)))));
        }

        return this;
    }
}
