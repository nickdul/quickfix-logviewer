package quickfix.logviewer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: Sol
 * DateTime: 2014/02/22 19:18
 */
public class ShowRemoveColumnsDialog extends JDialog
{
    public ShowRemoveColumnsDialog(Frame frame)
    {
        super(frame);
        setSize(300, 400);
        setLayout(new BorderLayout());
    }

    private ArrayList<TableColumn> removed = new ArrayList<TableColumn>();
    private JCheckBox[] boxes;

    public void display(final MessagesTable currentTable)
    {
        if (boxes == null) {
            int columnCount = currentTable.getColumnCount();
            boxes = new JCheckBox[columnCount];
            for (int i = 0; i < columnCount; i++) {
                String columnName = currentTable.getColumnName(i);
                JCheckBox box = new JCheckBox(columnName);
                box.setSelected(true);
                boxes[i] = box;
            }
            //
            add(new JPanel()
            {{
                    setLayout(new FlowLayout(FlowLayout.LEFT));
                    JTableHeader tableHeader = currentTable.getTableHeader();
                    for (JCheckBox box : boxes) {
                        add(box);
                    }
                }});
            add(new JPanel()
            {{
                    JButton applyBtn = new JButton("Apply");
                    add(applyBtn);
                    applyBtn.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int i = 0; i < boxes.length; i++) {
                                JCheckBox box = boxes[i];
                                if (box.isSelected()) {
                                    //TODO: add column back
                                }
                                if (!box.isSelected()) {
                                    //http://stackoverflow.com/questions/5270032/hide-column-in-jtable-temporary
                                    TableColumn column = currentTable.getColumnModel().getColumn(i);
                                    removed.add(column);
                                    //omce you removed a column columnIndex changes
                                    //currentTable.getColumnModel().removeColumn(column);
                                }
                            }
                            for (TableColumn tableColumn : removed) {
                                currentTable.removeColumn(tableColumn);
                            }
                            dispose();
                        }
                    });
                    JButton cancel = new JButton("Cancel");
                    add(cancel);
                    cancel.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            dispose();
                        }
                    });
                }}, BorderLayout.SOUTH);
        }
        setVisible(true);
    }
}
