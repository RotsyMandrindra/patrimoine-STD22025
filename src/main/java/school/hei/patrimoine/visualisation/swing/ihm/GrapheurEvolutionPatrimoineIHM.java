package school.hei.patrimoine.visualisation.swing.ihm;

import lombok.SneakyThrows;
import school.hei.patrimoine.visualisation.swing.modele.EvolutionPatrimoineObservable;
import school.hei.patrimoine.visualisation.xchart.GrapheurEvolutionPatrimoine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GrapheurEvolutionPatrimoineIHM extends JPanel implements Observer {
  private final GrapheurEvolutionPatrimoine grapheurEvolutionPatrimoine;
  private final EvolutionPatrimoineObservable evolutionPatrimoineObservable;

  public GrapheurEvolutionPatrimoineIHM(EvolutionPatrimoineObservable evolutionPatrimoineObservable) {
    super();
    this.grapheurEvolutionPatrimoine = new GrapheurEvolutionPatrimoine();

    this.evolutionPatrimoineObservable = evolutionPatrimoineObservable;
    this.evolutionPatrimoineObservable.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    this.repaint();
  }

  @SneakyThrows
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    var grapheFile = grapheurEvolutionPatrimoine.apply(evolutionPatrimoineObservable.getEvolutionPatrimoine());
    var grapheImage = ImageIO.read(grapheFile);
    g.drawImage(grapheImage, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}
