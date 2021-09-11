package RPRMovieApp.controllers.user.reservation.seatselection;

import RPRMovieApp.controllers.ChosenProjection;
import RPRMovieApp.controllers.ChosenSeats;
import RPRMovieApp.controllers.Price;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ChooseSeats1Controller
{
    public Button a1; public Button a2; public Button a3; public Button a4; public Button a5; public Button a6;public Button a7; public Button a8; public Button a9;public Button a10; public Button a11; public Button a12; public Button a13; public Button a14; public Button a15; public Button a16; public Button a17; public Button a18; public Button a19; public Button a20; public Button a21; public Button a22; public Button a23; public Button a24; public Button a25;
    public Button b1; public Button b2; public Button b3; public Button b4; public Button b5; public Button b6;public Button b7; public Button b8; public Button b9;public Button b10; public Button b11; public Button b12; public Button b13; public Button b14; public Button b15; public Button b16; public Button b17; public Button b18; public Button b19; public Button b20; public Button b21; public Button b22; public Button b23; public Button b24; public Button b25;
    public Button c1; public Button c2; public Button c3; public Button c4; public Button c5; public Button c6;public Button c7; public Button c8; public Button c9;public Button c10; public Button c11; public Button c12; public Button c13; public Button c14; public Button c15; public Button c16; public Button c17; public Button c18; public Button c19; public Button c20; public Button c21; public Button c22; public Button c23; public Button c24; public Button c25;
    public Button d1; public Button d2; public Button d3; public Button d4; public Button d5; public Button d6;public Button d7; public Button d8; public Button d9;public Button d10; public Button d11; public Button d12; public Button d13; public Button d14; public Button d15; public Button d16; public Button d17; public Button d18; public Button d19; public Button d20; public Button d21; public Button d22; public Button d23; public Button d24; public Button d25;
    public Button e1; public Button e2; public Button e3; public Button e4; public Button e5; public Button e6;public Button e7; public Button e8; public Button e9;public Button e10; public Button e11; public Button e12; public Button e13; public Button e14; public Button e15; public Button e16; public Button e17; public Button e18; public Button e19; public Button e20; public Button e21; public Button e22; public Button e23; public Button e24; public Button e25;
    public Button f1; public Button f2; public Button f3; public Button f4; public Button f5; public Button f6;public Button f7; public Button f8; public Button f9;public Button f10; public Button f11; public Button f12; public Button f13; public Button f14; public Button f15; public Button f16; public Button f17; public Button f18; public Button f19; public Button f20; public Button f21; public Button f22; public Button f23; public Button f24; public Button f25;
    public Button g1; public Button g2; public Button g3; public Button g4; public Button g5; public Button g6;public Button g7; public Button g8; public Button g9;public Button g10; public Button g11; public Button g12; public Button g13; public Button g14; public Button g15; public Button g16; public Button g17; public Button g18; public Button g19; public Button g20; public Button g21; public Button g22; public Button g23; public Button g24; public Button g25;
    public Button h1; public Button h2; public Button h3; public Button h4; public Button h5; public Button h6;public Button h7; public Button h8; public Button h9;public Button h10; public Button h11; public Button h12; public Button h13; public Button h14; public Button h15; public Button h16; public Button h17; public Button h18; public Button h19; public Button h20; public Button h21; public Button h22; public Button h23; public Button h24; public Button h25;
    public Button i1; public Button i2; public Button i3; public Button i4; public Button i5; public Button i6;public Button i7; public Button i8; public Button i9;public Button i10; public Button i11; public Button i12; public Button i13; public Button i14; public Button i15; public Button i16; public Button i17; public Button i18; public Button i19; public Button i20; public Button i21; public Button i22; public Button i23; public Button i24; public Button i25;
    public Button j1; public Button j2; public Button j3; public Button j4; public Button j5; public Button j6;public Button j7; public Button j8; public Button j9;public Button j10; public Button j11; public Button j12; public Button j13; public Button j14; public Button j15; public Button j16; public Button j17; public Button j18; public Button j19; public Button j20; public Button j21; public Button j22; public Button j23; public Button j24; public Button j25;
    public Button k1; public Button k2; public Button k3; public Button k4; public Button k5; public Button k6;public Button k7; public Button k8; public Button k9;public Button k10; public Button k11; public Button k12; public Button k13; public Button k14; public Button k15; public Button k16; public Button k17; public Button k18; public Button k19; public Button k20; public Button k21; public Button k22; public Button k23; public Button k24; public Button k25;
    public Button l1; public Button l2; public Button l3; public Button l4; public Button l5; public Button l6;public Button l7; public Button l8; public Button l9;public Button l10; public Button l11; public Button l12; public Button l13; public Button l14; public Button l15; public Button l16; public Button l17; public Button l18; public Button l19; public Button l20; public Button l21; public Button l22; public Button l23; public Button l24; public Button l25;
    public Button m1; public Button m2; public Button m3; public Button m4; public Button m5; public Button m6;public Button m7; public Button m8; public Button m9;public Button m10; public Button m11; public Button m12; public Button m13; public Button m14; public Button m15; public Button m16; public Button m17; public Button m18; public Button m19; public Button m20; public Button m21; public Button m22; public Button m23; public Button m24; public Button m25;
    public Button n1; public Button n2; public Button n3; public Button n4; public Button n5; public Button n6;public Button n7; public Button n8; public Button n9;public Button n10; public Button n11; public Button n12; public Button n13; public Button n14; public Button n15; public Button n16; public Button n17; public Button n18; public Button n19; public Button n20; public Button n21; public Button n22; public Button n23; public Button n24; public Button n25;
    public Button o1; public Button o2; public Button o3; public Button o4; public Button o5; public Button o6;public Button o7; public Button o8; public Button o9;public Button o10; public Button o11; public Button o12; public Button o13; public Button o14; public Button o15; public Button o16; public Button o17; public Button o18; public Button o19; public Button o20; public Button o21; public Button o22; public Button o23; public Button o24; public Button o25;

    private Connection conn;
    private PreparedStatement getTickets;

    private ArrayList<Integer> takenSeats = new ArrayList<>();

    public Button[][] seats;


    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        seats = new Button[][]
                {
                        {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25},
                        {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25},
                        {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25},
                        {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25},
                        {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25},
                        {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24, f25},
                        {g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25},
                        {h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23, h24, h25},
                        {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25},
                        {j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25},
                        {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15, k16, k17, k18, k19, k20, k21, k22, k23, k24, k25},
                        {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25},
                        {m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, m24, m25},
                        {n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n25},
                        {o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23, o24, o25}
                };
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        getTickets = conn.prepareStatement("SELECT * FROM ticket WHERE projectionid=?");
        getTickets.setInt(1, ChosenProjection.getChosenProjection().getId());
        ResultSet rs = getTickets.executeQuery();
        String taken = "-fx-background-color: lightpink";
        String available = "-fx-background-color: yellowgreen";
        String selected = "-fx-background-color: lightblue";

        while (rs.next())
        {
            takenSeats.add(rs.getInt(2));
        }
        int i, j;
        for (i = 0; i < 15; i++)
        {
            for (j = 0; j < 25; j++)
            {
                Button b = seats[i][j];
                int p = 25*i + j + 1;
                if (takenSeats.contains(p))
                {
                    b.setStyle(taken);
                }
                else if (ChosenSeats.getSeats().contains(p)) //Though this can't happen before user selects a seat
                {
                    b.setStyle(selected);
                }
                else
                {
                    b.setStyle(available);
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            if (b.getStyle().equals(available)) //Take a free seat
                            {
                                b.setStyle(selected);
                                ChosenSeats.getSeats().add(p);
                            }
                            else if (b.getStyle().equals(selected)) //Free a taken seat
                            {
                                b.setStyle(available);
                                ChosenSeats.getSeats().remove(p);
                            }
                        }
                    });
                }
            }
        }
    }

    public void takeSeatsClick(ActionEvent actionEvent) throws SQLException, IOException {
        Price.setPrice(ChosenSeats.getSeats().size()*6.0);
        Node n = (Node) actionEvent.getSource();
        Stage seatsStage = (Stage) n.getScene().getWindow();
        seatsStage.close();
        conn.close();

        Stage chooseSeatsNoteStage = new Stage();
        FXMLLoader chooseSeatsNoteLoader = new FXMLLoader(getClass().getResource("/fxml/chooseSeatsNote.fxml")); //This path is temporary
        Parent root = chooseSeatsNoteLoader.load();
        chooseSeatsNoteStage.setTitle("NOTE!");
        chooseSeatsNoteStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        chooseSeatsNoteStage.setResizable(false);
        chooseSeatsNoteStage.show();
    }
}
