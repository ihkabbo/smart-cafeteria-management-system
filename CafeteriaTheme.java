package cafeteriamanagementsystem.gui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * =============================================================
 * CafeteriaTheme  ("Venus" green style, mobile-app inspired)
 * =============================================================
 * A purely COSMETIC re-skin for the Cafeteria Management System.
 * The palette and shapes are modeled after a fresh-tea /
 * grocery-app mobile UI: pale mint background, bright leafy-green
 * accents, white cards, and fully-rounded "pill" buttons.
 *
 * WHAT THIS CLASS DOES:
 *  - Walks the component tree that a form has ALREADY built
 *    (labels, buttons, fields, tables, panels, combo boxes...)
 *    and re-colors / re-fonts / re-borders them.
 *
 * WHAT THIS CLASS NEVER DOES:
 *  - It never adds, removes, or renames any field, method,
 *    button, or listener.
 *  - It never touches DAO/service/business logic.
 *  - It never changes layout positions (setBounds values),
 *    window sizes, or navigation flow.
 *
 * HOW TO USE:
 *  In any JFrame constructor, add ONE line right before
 *  `setVisible(true);`:
 *
 *      CafeteriaTheme.apply(this);
 *
 * That's it — everything already added to the frame gets themed.
 * =============================================================
 */
public final class CafeteriaTheme {

    // ---------------------------------------------------------
    // PALETTE — "Venus" leafy-green style
    // ---------------------------------------------------------
    public static final Color BG_MINT      = new Color(0xEE, 0xF6, 0xE6); // page background (pale mint)
    public static final Color BG_CARD      = new Color(0xFF, 0xFF, 0xFF); // panels / cards
    public static final Color GREEN_DARK   = new Color(0x28, 0x4B, 0x1F); // titles / header text
    public static final Color GREEN_MED    = new Color(0x5B, 0x9A, 0x2F); // table headers / sub-accents
    public static final Color GREEN        = new Color(0x7C, 0xB3, 0x42); // buttons / primary accent
    public static final Color GREEN_DARKER = new Color(0x63, 0x9A, 0x2E); // button hover
    public static final Color BORDER_SOFT  = new Color(0xD9, 0xEC, 0xC6); // field/table borders
    public static final Color TEXT_DARK    = new Color(0x2C, 0x2C, 0x2C); // body text
    public static final Color TEXT_MUTED   = new Color(0x8A, 0x93, 0x86); // secondary/placeholder text
    public static final Color STRIPE       = new Color(0xF4, 0xFA, 0xEE); // table alt rows
    public static final Color WHITE        = Color.WHITE;

    private static final String FONT_FAMILY = "Segoe UI";
    private static final Font FIELD_FONT  = new Font(FONT_FAMILY, Font.PLAIN, 13);
    private static final Font TABLE_FONT  = new Font(FONT_FAMILY, Font.PLAIN, 12);
    private static final Font HEADER_FONT = new Font(FONT_FAMILY, Font.BOLD, 12);

    private static boolean globalDefaultsApplied = false;

    private CafeteriaTheme() {
        // static-only helper
    }

    /** Apply the theme to every component already added to this frame. */
    public static void apply(JFrame frame) {
        if (frame == null) {
            return;
        }

        applyGlobalDefaultsOnce();

        frame.getContentPane().setBackground(BG_MINT);
        styleContainer(frame.getContentPane());
        frame.repaint();
    }

    /** Overload for plain windows/dialogs built the same way (e.g. detail popups). */
    public static void apply(JDialog dialog) {
        if (dialog == null) {
            return;
        }

        applyGlobalDefaultsOnce();

        dialog.getContentPane().setBackground(BG_MINT);
        styleContainer(dialog.getContentPane());
        dialog.repaint();
    }

    // ---------------------------------------------------------
    // Global look & feel touch-ups (JOptionPane dialogs, etc.)
    // Runs once for the whole app.
    // ---------------------------------------------------------
    private static void applyGlobalDefaultsOnce() {
        if (globalDefaultsApplied) {
            return;
        }
        globalDefaultsApplied = true;

        UIManager.put("OptionPane.background", BG_MINT);
        UIManager.put("Panel.background", BG_MINT);
        UIManager.put("OptionPane.messageForeground", TEXT_DARK);
        UIManager.put("Button.background", GREEN);
        UIManager.put("Button.foreground", WHITE);
    }

    // ---------------------------------------------------------
    // Recursive walk
    // ---------------------------------------------------------
    private static void styleContainer(Container container) {
        for (Component comp : container.getComponents()) {

            if (comp instanceof JLabel) {
                styleLabel((JLabel) comp);

            } else if (comp instanceof JButton) {
                styleButton((JButton) comp);

            } else if (comp instanceof JTable) {
                styleTable((JTable) comp);

            } else if (comp instanceof JTextArea) {
                styleTextArea((JTextArea) comp);

            } else if (comp instanceof JTextField) {
                // covers JTextField and JPasswordField
                styleTextField((JTextField) comp);

            } else if (comp instanceof JComboBox) {
                styleComboBox((JComboBox<?>) comp);

            } else if (comp instanceof JSpinner) {
                styleSpinner((JSpinner) comp);

            } else if (comp instanceof JScrollPane) {
                styleScrollPane((JScrollPane) comp);

            } else if (comp instanceof JTabbedPane) {
                styleTabbedPane((JTabbedPane) comp);

            } else if (comp instanceof JPanel) {
                stylePanel((JPanel) comp);
            }

            // Recurse into anything that can hold children
            if (comp instanceof Container) {
                styleContainer((Container) comp);
            }
        }
    }

    // ---------------------------------------------------------
    // Individual component stylers
    // ---------------------------------------------------------

    private static void styleLabel(JLabel label) {
        Font old = label.getFont();
        boolean looksLikeTitle = old != null && old.isBold() && old.getSize() >= 16;

        if (old != null) {
            label.setFont(new Font(FONT_FAMILY, old.getStyle(), old.getSize()));
        }

        label.setForeground(looksLikeTitle ? GREEN_DARK : TEXT_DARK);
    }

    /**
     * Fully-rounded "pill" buttons, like the Purchased / Next buttons
     * in the reference UI. We install a small custom ButtonUI that
     * paints the rounded, filled pill shape BEFORE the text/icon is
     * drawn, so the label always stays on top and readable.
     */
    private static void styleButton(JButton button) {
        int size = (button.getFont() != null) ? button.getFont().getSize() : 13;

        button.setFont(new Font(FONT_FAMILY, Font.BOLD, size));
        button.setForeground(WHITE);
        button.setBackground(GREEN);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setUI(new PillButtonUI());

        // Simple hover feedback — purely visual, no behavior change.
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(GREEN_DARKER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(GREEN);
            }
        });
    }

    /** Rounded, pale-bordered field to echo the reference app's pill search bar. */
    private static void styleTextField(JTextField field) {
        field.setFont(FIELD_FONT);
        field.setForeground(TEXT_DARK);
        field.setBackground(WHITE);
        field.setCaretColor(GREEN_DARK);
        field.setOpaque(true);
        field.setBorder(new PillOutlineBorder(BORDER_SOFT, 18));
    }

    private static void styleTextArea(JTextArea area) {
        area.setFont(FIELD_FONT);
        area.setForeground(TEXT_DARK);
        area.setBackground(WHITE);
        area.setCaretColor(GREEN_DARK);
        area.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_SOFT, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
    }

    private static void styleComboBox(JComboBox<?> combo) {
        combo.setFont(FIELD_FONT);
        combo.setBackground(WHITE);
        combo.setForeground(TEXT_DARK);
    }

    private static void styleSpinner(JSpinner spinner) {
        spinner.setFont(FIELD_FONT);
        spinner.setBorder(BorderFactory.createLineBorder(BORDER_SOFT, 1, true));
    }

    @SuppressWarnings("serial")
    private static void styleTable(JTable table) {
        table.setFont(TABLE_FONT);
        table.setForeground(TEXT_DARK);
        table.setBackground(WHITE);
        table.setRowHeight(26);
        table.setGridColor(BORDER_SOFT);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(0xDD, 0xF0, 0xC9)); // soft green highlight
        table.setSelectionForeground(GREEN_DARK);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setFont(HEADER_FONT);
            header.setBackground(GREEN);
            header.setForeground(WHITE);
            header.setOpaque(true);
        }

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        t, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? WHITE : STRIPE);
                    c.setForeground(TEXT_DARK);
                }

                return c;
            }
        });
    }

    /** White "card" look for scrollable lists/tables. */
    private static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_SOFT, 1, true));
        if (scrollPane.getViewport() != null) {
            scrollPane.getViewport().setBackground(WHITE);
        }
    }

    private static void styleTabbedPane(JTabbedPane tabs) {
        tabs.setFont(new Font(FONT_FAMILY, Font.BOLD, 13));
        tabs.setBackground(BG_MINT);
        tabs.setForeground(GREEN_DARK);
    }

    private static void stylePanel(JPanel panel) {
        panel.setBackground(BG_MINT);
    }

    // ---------------------------------------------------------
    // PillButtonUI — paints a rounded, filled pill background
    // BEFORE the button's text/icon, so the label is never
    // covered. Purely a drawing helper; carries no logic.
    // ---------------------------------------------------------
    private static final class PillButtonUI extends BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = c.getHeight();
            g2.setColor(c.getBackground());
            g2.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arc, arc);
            g2.dispose();

            // Paints text/icon only (background fill is skipped because
            // contentAreaFilled was set to false in styleButton()).
            super.paint(g, c);
        }
    }

    // ---------------------------------------------------------
    // PillOutlineBorder — draws a rounded STROKE only (never a
    // fill), so it can safely be painted after a text field's
    // contents without ever covering the typed text.
    // ---------------------------------------------------------
    private static final class PillOutlineBorder extends AbstractBorder {

        private final Color color;
        private final int arc;

        PillOutlineBorder(Color color, int arc) {
            this.color = color;
            this.arc = arc;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int effectiveArc = Math.min(arc, height - 1);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, effectiveArc, effectiveArc);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 10, 4, 10);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(4, 10, 4, 10);
            return insets;
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
