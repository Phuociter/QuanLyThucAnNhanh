/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class NonEditableTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column)
    {
       return false; 
    }
}
