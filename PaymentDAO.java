package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // =====================================================
    // ADD PAYMENT
    // =====================================================

    public boolean addPayment(Payment payment) {

        String sql =
                "INSERT INTO payments " +
                "(order_id, payment_method, payment_account, " +
                "amount, payment_status, payment_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, payment.getOrderId());

            pst.setString(
                    2,
                    payment.getPaymentMethod()
            );

            pst.setString(
                    3,
                    payment.getPaymentAccount()
            );

            pst.setDouble(
                    4,
                    payment.getAmount()
            );

            pst.setString(
                    5,
                    payment.getPaymentStatus()
            );

            if (payment.getPaymentDate() != null) {

                pst.setTimestamp(
                        6,
                        Timestamp.valueOf(
                                payment.getPaymentDate()
                        )
                );

            } else {

                pst.setTimestamp(
                        6,
                        new Timestamp(
                                System.currentTimeMillis()
                        )
                );
            }

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // GET PAYMENT BY ID
    // =====================================================

    public Payment getPaymentById(int paymentId) {

        String sql =
                "SELECT * FROM payments " +
                "WHERE payment_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setInt(1, paymentId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    Payment payment =
                            new Payment();

                    payment.setPaymentId(
                            rs.getInt("payment_id")
                    );

                    payment.setOrderId(
                            rs.getInt("order_id")
                    );

                    payment.setPaymentMethod(
                            rs.getString(
                                    "payment_method"
                            )
                    );

                    payment.setPaymentAccount(
                            rs.getString(
                                    "payment_account"
                            )
                    );

                    payment.setAmount(
                            rs.getDouble("amount")
                    );

                    payment.setPaymentStatus(
                            rs.getString(
                                    "payment_status"
                            )
                    );

                    if (rs.getTimestamp(
                            "payment_date"
                    ) != null) {

                        payment.setPaymentDate(
                                rs.getTimestamp(
                                        "payment_date"
                                ).toLocalDateTime()
                        );
                    }

                    return payment;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // GET ALL PAYMENTS
    // =====================================================

    public List<Payment> getAllPayments() {

        List<Payment> payments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM payments " +
                "ORDER BY payment_id DESC";

        try (Connection con =
                     DatabaseConnection.getConnection();
             Statement st =
                     con.createStatement();
             ResultSet rs =
                     st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment =
                        new Payment();

                payment.setPaymentId(
                        rs.getInt("payment_id")
                );

                payment.setOrderId(
                        rs.getInt("order_id")
                );

                payment.setPaymentMethod(
                        rs.getString(
                                "payment_method"
                        )
                );

                payment.setPaymentAccount(
                        rs.getString(
                                "payment_account"
                        )
                );

                payment.setAmount(
                        rs.getDouble("amount")
                );

                payment.setPaymentStatus(
                        rs.getString(
                                "payment_status"
                        )
                );

                if (rs.getTimestamp(
                        "payment_date"
                ) != null) {

                    payment.setPaymentDate(
                            rs.getTimestamp(
                                    "payment_date"
                            ).toLocalDateTime()
                    );
                }

                payments.add(payment);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return payments;
    }


    // =====================================================
    // UPDATE PAYMENT STATUS
    // =====================================================

    public boolean updatePaymentStatus(
            int paymentId,
            String paymentStatus) {

        String sql =
                "UPDATE payments " +
                "SET payment_status=? " +
                "WHERE payment_id=?";

        try (Connection con =
                     DatabaseConnection.getConnection();
             PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setString(
                    1,
                    paymentStatus
            );

            pst.setInt(
                    2,
                    paymentId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // DELETE PAYMENT
    // =====================================================

    public boolean deletePayment(int paymentId) {

        String sql =
                "DELETE FROM payments " +
                "WHERE payment_id=?";

        try (Connection con =
                     DatabaseConnection.getConnection();
             PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setInt(1, paymentId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}