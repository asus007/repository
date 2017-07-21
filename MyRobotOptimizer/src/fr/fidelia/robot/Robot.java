package fr.fidelia.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Robot implements IRobot {

    private static final int MAX_SIZE = 10;
    private static final String SEPARATOR = "/";

    public static void main(String[] args) {
        Robot robot = new Robot();

        robot.traiterArticles(null); // Cas null

        robot.traiterArticles(new ArrayList<>()); // Cas vide

        List<Article> articles = new ArrayList<>();

        Article article = new Article();
        article.setWeigt(1);
        articles.add(article);

        Article article2 = new Article();
        article2.setWeigt(6);
        articles.add(article2);

        Article article3 = new Article();
        article3.setWeigt(3);
        articles.add(article3);

        Article article4 = new Article();
        article4.setWeigt(8);
        articles.add(article4);

        Article article5 = new Article();
        article5.setWeigt(4);
        articles.add(article5);

        Article article6 = new Article();
        article6.setWeigt(1);
        articles.add(article6);

        Article article7 = new Article();
        article7.setWeigt(6);
        articles.add(article7);

        Article article8 = new Article();
        article8.setWeigt(8);
        articles.add(article8);

        Article article9 = new Article();
        article9.setWeigt(9);
        articles.add(article9);

        Article article10 = new Article();
        article10.setWeigt(5);
        articles.add(article10);

        Article article11 = new Article();
        article11.setWeigt(2);
        articles.add(article11);

        Article article12 = new Article();
        article12.setWeigt(5);
        articles.add(article12);

        Article article13 = new Article();
        article13.setWeigt(7);
        articles.add(article13);

        Article article14 = new Article();
        article14.setWeigt(7);
        articles.add(article14);

        Article article15 = new Article();
        article15.setWeigt(3);
        articles.add(article15);

        robot.traiterArticles(articles);
    }

    public void traiterArticles(List<Article> liste) {
        if (liste == null || liste.isEmpty()) {
            return;
        }
        System.out.println("Articles : " + getStringArticles(liste));

        Collection<String> filledPackages = getPackages(liste, MAX_SIZE);
        String stringPackage = getStringPackages(filledPackages);

        System.out.println("Robot optimise : "
                + String.format(": %s => " + "%s cartons utilisés", stringPackage, filledPackages.size()));
    }

    private Collection<String> getPackages(List<Article> liste, int capacity) {
        Map<Integer, String> packages = new HashMap<>();
        if (liste != null && capacity > 0) {

            // Tri ordre décroissant de la liste
            liste.sort(new Comparator<Article>() {
                @Override
                public int compare(Article a1, Article a2) {
                    return a2.getWeigt() - a1.getWeigt();
                }
            });

            // Initialisation de la place disponible dans chaque "boite" que l'on souhaite remplir
            int[] boxes = new int[liste.size()];
            for (int i = 0; i < boxes.length; i++) {
                boxes[i] = capacity;
            }

            // Insertion de chaque élément dans la première boite qui a suffisamment de place
            liste.forEach(item -> {
                if (item != null) {
                    for (int i = 0; i < boxes.length; i++) {
                        if (boxes[i] - item.getWeigt() >= 0) {
                            boxes[i] -= item.getWeigt();
                            packages.put(i, packages.containsKey(i) ? packages.get(i) + String.valueOf(item.getWeigt())
                                    : String.valueOf(item.getWeigt()));
                            break;
                        }
                    }
                }
            });
        }
        return packages.values();
    }

    private String getStringPackages(Collection<String> filledPackages) {
        StringBuilder stringPackages = new StringBuilder();
        if (filledPackages != null) {
            filledPackages.forEach(s -> {
                stringPackages.append(s);
                stringPackages.append(SEPARATOR);
            });
        }
        return stringPackages.toString();
    }

    private String getStringArticles(List<Article> liste) {

        StringBuilder stringArticles = new StringBuilder();
        liste.forEach(s -> {
            stringArticles.append(s.getWeigt());
        });

        return stringArticles.toString();
    }

}
