package RPRMovieApp.controllers;

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

public class ChooseSeats2Controller
{
    public Button a1; public Button a2; public Button a3; public Button a4; public Button a5; public Button a6;public Button a7; public Button a8; public Button a9;public Button a10; public Button a11; public Button a12; public Button a13; public Button a14; public Button a15; public Button a16; public Button a17; public Button a18; public Button a19; public Button a20;
    public Button b1; public Button b2; public Button b3; public Button b4; public Button b5; public Button b6;public Button b7; public Button b8; public Button b9;public Button b10; public Button b11; public Button b12; public Button b13; public Button b14; public Button b15; public Button b16; public Button b17; public Button b18; public Button b19; public Button b20;
    public Button c1; public Button c2; public Button c3; public Button c4; public Button c5; public Button c6;public Button c7; public Button c8; public Button c9;public Button c10; public Button c11; public Button c12; public Button c13; public Button c14; public Button c15; public Button c16; public Button c17; public Button c18; public Button c19; public Button c20;
    public Button d1; public Button d2; public Button d3; public Button d4; public Button d5; public Button d6;public Button d7; public Button d8; public Button d9;public Button d10; public Button d11; public Button d12; public Button d13; public Button d14; public Button d15; public Button d16; public Button d17; public Button d18; public Button d19; public Button d20;
    public Button e1; public Button e2; public Button e3; public Button e4; public Button e5; public Button e6;public Button e7; public Button e8; public Button e9;public Button e10; public Button e11; public Button e12; public Button e13; public Button e14; public Button e15; public Button e16; public Button e17; public Button e18; public Button e19; public Button e20;
    public Button f1; public Button f2; public Button f3; public Button f4; public Button f5; public Button f6;public Button f7; public Button f8; public Button f9;public Button f10; public Button f11; public Button f12; public Button f13; public Button f14; public Button f15; public Button f16; public Button f17; public Button f18; public Button f19; public Button f20;
    public Button g1; public Button g2; public Button g3; public Button g4; public Button g5; public Button g6;public Button g7; public Button g8; public Button g9;public Button g10; public Button g11; public Button g12; public Button g13; public Button g14; public Button g15; public Button g16; public Button g17; public Button g18; public Button g19; public Button g20;
    public Button h1; public Button h2; public Button h3; public Button h4; public Button h5; public Button h6;public Button h7; public Button h8; public Button h9;public Button h10; public Button h11; public Button h12; public Button h13; public Button h14; public Button h15; public Button h16; public Button h17; public Button h18; public Button h19; public Button h20;
    public Button i1; public Button i2; public Button i3; public Button i4; public Button i5; public Button i6;public Button i7; public Button i8; public Button i9;public Button i10; public Button i11; public Button i12; public Button i13; public Button i14; public Button i15; public Button i16; public Button i17; public Button i18; public Button i19; public Button i20;
    public Button j1; public Button j2; public Button j3; public Button j4; public Button j5; public Button j6;public Button j7; public Button j8; public Button j9;public Button j10; public Button j11; public Button j12; public Button j13; public Button j14; public Button j15; public Button j16; public Button j17; public Button j18; public Button j19; public Button j20;
    public Button k1; public Button k2; public Button k3; public Button k4; public Button k5; public Button k6;public Button k7; public Button k8; public Button k9;public Button k10; public Button k11; public Button k12; public Button k13; public Button k14; public Button k15; public Button k16; public Button k17; public Button k18; public Button k19; public Button k20;
    public Button l1; public Button l2; public Button l3; public Button l4; public Button l5; public Button l6;public Button l7; public Button l8; public Button l9;public Button l10; public Button l11; public Button l12; public Button l13; public Button l14; public Button l15; public Button l16; public Button l17; public Button l18; public Button l19; public Button l20;
    public Button m1; public Button m2; public Button m3; public Button m4; public Button m5; public Button m6;public Button m7; public Button m8; public Button m9;public Button m10; public Button m11; public Button m12; public Button m13; public Button m14; public Button m15; public Button m16; public Button m17; public Button m18; public Button m19; public Button m20;
    public Button n1; public Button n2; public Button n3; public Button n4; public Button n5; public Button n6;public Button n7; public Button n8; public Button n9;public Button n10; public Button n11; public Button n12; public Button n13; public Button n14; public Button n15; public Button n16; public Button n17; public Button n18; public Button n19; public Button n20;
    public Button o1; public Button o2; public Button o3; public Button o4; public Button o5; public Button o6;public Button o7; public Button o8; public Button o9;public Button o10; public Button o11; public Button o12; public Button o13; public Button o14; public Button o15; public Button o16; public Button o17; public Button o18; public Button o19; public Button o20;

    private Connection conn;
    private PreparedStatement getTickets;

    private ArrayList<Integer> takenSeats = new ArrayList<>();

    public Button[][] seats;


    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        seats = new Button[][]
                {
                        {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20},
                        {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20},
                        {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20},
                        {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20},
                        {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20},
                        {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20},
                        {g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20},
                        {h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20},
                        {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20},
                        {j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20},
                        {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15, k16, k17, k18, k19, k20},
                        {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20},
                        {m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20},
                        {n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20},
                        {o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20}
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
            for (j = 0; j < 20; j++)
            {
                Button b = seats[i][j];
                int p = 20*i + j + 376;
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
