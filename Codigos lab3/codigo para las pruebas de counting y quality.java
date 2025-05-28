import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Main {
    public static class Game {
        private String name;
        private String category;
        private int price;
        private int quality;

        public Game(String name, String category, int price, int quality) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quality = quality;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public int getPrice() { return price; }
        public int getQuality() { return quality; }

        public String toString() {
            return name + " - " + category + " - CLP" + price + " - Calidad: " + quality;
        }
    }

    public static class GenerateData {
        private static final String[] nombre = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior",
                "Shadow", "Kingdom", "Adventure", "Chronicle", "Hero", "Dark"};
        private static final String[] categoria = {"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación",
                "Puzzle", "Terror", "Plataformas", "MMO", "Carreras", "Lucha"};

        public static ArrayList<Game> generateGames(int n) {
            ArrayList<Game> games = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < n; i++) {
                String Nombre = nombre[random.nextInt(nombre.length)] +
                        nombre[random.nextInt(nombre.length)];

                String Categoria = categoria[random.nextInt(categoria.length)];

                int precio = random.nextInt(70000);

                int calidad = random.nextInt(100);

                games.add(new Game(Nombre, Categoria, precio, calidad));
            }

            return games;
        }
    }
    public static class Dataset {
        private ArrayList<Game> data;
        private String sortedByAttribute;

        public Dataset(ArrayList<Game> data) {
            this.data = new ArrayList<>(data);
            this.sortedByAttribute = null;
        }

        public ArrayList<Game> getGamesByPrice(int price) {
            ArrayList<Game> result = new ArrayList<>();
            if ("price".equals(sortedByAttribute)) {
                int cant = binarySearchByPrice(price);
                if (cant != -1) {
                    result.add(data.get(cant));
                    int left = cant - 1;
                    while (left >= 0 && data.get(left).getPrice() == price) {
                        result.add(data.get(left));
                        left--;
                    }
                    int right = cant + 1;
                    while (right < data.size() && data.get(right).getPrice() == price) {
                        result.add(data.get(right));
                        right++;
                    }
                }
            }
            else {
                for (int i = 0; i < data.size(); i++) {
                    Game game = data.get(i);
                    if (game.getPrice() == price) {
                        result.add(game);
                    }
                }
            }
            return result;
        }

        private int binarySearchByPrice(int price) {
            int left = 0;
            int right = data.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrice();

                if (midPrice == price) {
                    return mid;
                } else if (midPrice < price) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice) {
            ArrayList<Game> result = new ArrayList<>();
            if ("price".equals(sortedByAttribute)) {
                int start = primero(lowerPrice);
                int end = ultimo(higherPrice);

                if (start != -1 && end != -1) {
                    for (int i = start; i <= end; i++) {
                        result.add(data.get(i));
                    }
                }
            } else {
                for (int i = 0; i < data.size(); i++) {
                    Game game = data.get(i);
                    int gamePrice = game.getPrice();
                    if (gamePrice >= lowerPrice && gamePrice <= higherPrice) {
                        result.add(game);
                    }
                }
            }
            return result;
        }

        private int primero(int price) {
            int left = 0;
            int right = data.size() - 1;
            int result = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrice();

                if (midPrice >= price) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        private int ultimo(int price) {
            int left = 0;
            int right = data.size() - 1;
            int result = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrice();

                if (midPrice <= price) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
        }

        public ArrayList<Game> getGamesByCategory(String category) {
            ArrayList<Game> result = new ArrayList<>();
            if ("category".equals(sortedByAttribute)) {
                int cant = binarySearchByCategory(category);
                if (cant != -1) {
                    result.add(data.get(cant));

                    int left = cant - 1;
                    while (left >= 0 && data.get(left).getCategory().equals(category)) {
                        result.add(data.get(left));
                        left--;
                    }

                    int right = cant + 1;
                    while (right < data.size() && data.get(right).getCategory().equals(category)) {
                        result.add(data.get(right));
                        right++;
                    }
                }
            } else {
                for (int i = 0; i < data.size(); i++) {
                    Game game = data.get(i);
                    if (game.getCategory().equals(category)) {
                        result.add(game);
                    }
                }
            }
            return result;
        }

        private int binarySearchByCategory(String category) {
            int left = 0;
            int right = data.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int alp = data.get(mid).getCategory().compareTo(category);

                if (alp == 0) {
                    return mid;
                } else if (alp < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        public ArrayList<Game> getGamesByQuality(int quality) {
            ArrayList<Game> result = new ArrayList<>();
            if ("quality".equals(sortedByAttribute)) {
                int cant = binarySearchByQuality(quality);
                if (cant != -1) {
                    result.add(data.get(cant));

                    int left = cant - 1;
                    while (left >= 0 && data.get(left).getQuality() == quality) {
                        result.add(data.get(left));
                        left--;
                    }

                    int right = cant + 1;
                    while (right < data.size() && data.get(right).getQuality() == quality) {
                        result.add(data.get(right));
                        right++;
                    }
                }
            } else {
                for (int i = 0; i < data.size(); i++) {
                    Game game = data.get(i);
                    if (game.getQuality() == quality) {
                        result.add(game);
                    }
                }
            }
            return result;
        }

        private int binarySearchByQuality(int quality) {
            int left = 0;
            int right = data.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midQuality = data.get(mid).getQuality();

                if (midQuality == quality) {
                    return mid;
                } else if (midQuality < quality) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        public ArrayList<Game> sortbyalgorithm(String algorithm, String attribute) {
            Comparator<Game> comparator = getComparator(attribute);

            String alg = algorithm.toLowerCase();
            if (alg.equals("bubblesort")) {
                bubbleSort(comparator);
            }
            else if (alg.equals("insertionsort")) {
                insertionSort(comparator);
            }
            else if (alg.equals("selectionsort")) {
                selectionSort(comparator);
            }
            else if (alg.equals("mergesort")) {
                mergeSort(comparator);
            }
            else if (alg.equals("quicksort")) {
                quickSort(comparator);
            }
            else if (alg.equals("Counting Sort")) {
                quickSort(comparator);
            }
            else {
                Collections.sort(data, comparator);
            }

            sortedByAttribute = attribute;
            return new ArrayList<>(data);
        }

        private Comparator<Game> getComparator(String attribute) {
            String attr = attribute.toLowerCase();
            if (attr.equals("category")) {
                return Comparator.comparing(Game::getCategory);
            }
            else if (attr.equals("quality")) {
                return Comparator.comparingInt(Game::getQuality);
            }
            else {
                return Comparator.comparingInt(Game::getPrice);
            }
        }

        private void bubbleSort(Comparator<Game> comparator) {
            int n = data.size();
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n-i-1; j++) {
                    if (comparator.compare(data.get(j), data.get(j+1)) > 0) {
                        Collections.swap(data, j, j+1);
                    }
                }
            }
        }

        private void insertionSort(Comparator<Game> comparator) {
            for (int i = 1; i < data.size(); i++) {
                Game key = data.get(i);
                int j = i - 1;

                while (j >= 0 && comparator.compare(data.get(j), key) > 0) {
                    data.set(j+1, data.get(j));
                    j--;
                }
                data.set(j+1, key);
            }
        }

        private void selectionSort(Comparator<Game> comparator) {
            for (int i = 0; i < data.size()-1; i++) {
                int min = i;
                for (int j = i+1; j < data.size(); j++) {
                    if (comparator.compare(data.get(j), data.get(min)) < 0) {
                        min = j;
                    }
                }
                if (min != i) {
                    Collections.swap(data, i, min);
                }
            }
        }

        private void mergeSort(Comparator<Game> comparator) {
            mergeSorth(0, data.size()-1, comparator);
        }

        private void mergeSorth(int left, int right, Comparator<Game> comparator) {
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSorth(left, mid, comparator);
                mergeSorth(mid+1, right, comparator);
                merge(left, mid, right, comparator);
            }
        }

        private void merge(int left, int mid, int right, Comparator<Game> comparator) {
            ArrayList<Game> temp = new ArrayList<>(right - left + 1);
            int i = left, j = mid + 1;

            while (i <= mid && j <= right) {
                if (comparator.compare(data.get(i), data.get(j)) <= 0) {
                    temp.add(data.get(i++));
                } else {
                    temp.add(data.get(j++));
                }
            }

            while (i <= mid) {
                temp.add(data.get(i++));
            }

            while (j <= right) {
                temp.add(data.get(j++));
            }

            for (int k = 0; k < temp.size(); k++) {
                data.set(left + k, temp.get(k));
            }
        }

        private void quickSort(Comparator<Game> comparator) {
            quickSorth(0, data.size()-1, comparator);
        }

        private void quickSorth(int low, int high, Comparator<Game> comparator) {
            if (low < high) {
                int pi = partition(low, high, comparator);
                quickSorth(low, pi-1, comparator);
                quickSorth(pi+1, high, comparator);
            }
        }
        private void countingSortByQuality() {

            int[] count = new int[101];


            for (Game game : data) {
                count[game.getQuality()]++;
            }


            for (int i = 1; i <= 100; i++) {
                count[i] += count[i-1];
            }


            Game[] output = new Game[data.size()];
            for (int i = data.size()-1; i >= 0; i--) {
                Game game = data.get(i);
                output[--count[game.getQuality()]] = game;
            }

            for (int i = 0; i < data.size(); i++) {
                data.set(i, output[i]);
            }
        }
        private int partition(int low, int high, Comparator<Game> comparator) {
            Game pivot = data.get(high);
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (comparator.compare(data.get(j), pivot) <= 0) {
                    i++;
                    Collections.swap(data, i, j);
                }
            }

            Collections.swap(data, i+1, high);
            return i+1;
        }
    }
    public static void main(String[] args) {
        ArrayList<Game> juegos = GenerateData.generateGames(100);
        Dataset dataset = new Dataset(juegos);


        ArrayList<Game> sortedGames = dataset.sortbyalgorithm("Counting Sort", "quality");
        System.out.println("Arreglo ordenado por calidad:");
        for (Game game : sortedGames) {
            System.out.println(game);
        }
    }
}