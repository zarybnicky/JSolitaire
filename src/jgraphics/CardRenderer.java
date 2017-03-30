/*
 * 
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
    
    ImageIcon back = new ImageIcon(Card.class.getResource("/resources/BACK.gif"), "BACK");

    @Override
    public Component getListCellRendererComponent(JList<? extends Card> list, Card card, int index,
            boolean isSelected, boolean cellHasFocus) {

        if (card.isFaceUp()){
            setIcon(card.getIcon());
        }else{
            setIcon(back);
        }
        //setIcon(card.getIcon()); // Pri testovani je doporuceno odkomentovat.
        if (getIcon() != null) {
            if (index != list.getModel().getSize() - 1) {
                setIcon(new ImageIcon(createImage(new FilteredImageSource(
                        ((ImageIcon) getIcon()).getImage().getSource(),
                        new CropImageFilter(0, 0, getIcon().getIconWidth(), 17)))));
            }
        } else {
            setIcon(new ImageIcon("/resources/missing.gif"));
        }

        return this;
    }
}
