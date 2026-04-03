import java.awt.*;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

class Film {
    private String title;
    private String genre;
    private double rating;
    private int year;
    private String studio;
    
    public Film(String title, String genre, double rating, int year, String studio) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.studio = studio;
    }
    
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
    public int getYear() { return year; }
    public String getStudio() { return studio; }
}

class FilmLibraryModel {
    private List<Film> films;
    
    public FilmLibraryModel() {
        this.films = new ArrayList<>();
        initializeData();
    }
    
    private void initializeData() {
        addFilm(new Film("Побег из Шоушенка", "Драма", 9.3, 1994, "Castle Rock"));
        addFilm(new Film("Крестный отец", "Драма", 9.2, 1972, "Paramount"));
        addFilm(new Film("Крестный отец 2", "Драма", 9.0, 1974, "Paramount"));
        addFilm(new Film("Темный рыцарь", "Боевик", 9.0, 2008, "Warner Bros"));
        addFilm(new Film("12 разгневанных мужчин", "Драма", 9.0, 1957, "United Artists"));
        addFilm(new Film("Список Шиндлера", "Драма", 8.9, 1993, "Universal"));
        addFilm(new Film("Властелин колец: Возвращение короля", "Фэнтези", 8.9, 2003, "New Line"));
        addFilm(new Film("Криминальное чтиво", "Криминал", 8.9, 1994, "Miramax"));
        addFilm(new Film("Хороший плохой злой", "Вестерн", 8.8, 1966, "United Artists"));
        addFilm(new Film("Бойцовский клуб", "Драма", 8.8, 1999, "Fox"));
        addFilm(new Film("Форрест Гамп", "Драма", 8.8, 1994, "Paramount"));
        addFilm(new Film("Начало", "Фантастика", 8.8, 2010, "Warner Bros"));
        addFilm(new Film("Властелин колец: Братство кольца", "Фэнтези", 8.8, 2001, "New Line"));
        addFilm(new Film("Матрица", "Фантастика", 8.7, 1999, "Warner Bros"));
        addFilm(new Film("Интерстеллар", "Фантастика", 8.6, 2014, "Paramount"));
        addFilm(new Film("Зеленая миля", "Драма", 8.6, 1999, "Castle Rock"));
        addFilm(new Film("Гладиатор", "Боевик", 8.5, 2000, "DreamWorks"));
        addFilm(new Film("1+1", "Драма", 8.5, 2011, "Gaumont"));
        addFilm(new Film("Джокер", "Драма", 8.4, 2019, "Warner Bros"));
        addFilm(new Film("Титаник", "Драма", 7.9, 1997, "Paramount"));
        addFilm(new Film("Аватар", "Фантастика", 7.9, 2009, "Fox"));
        addFilm(new Film("Дюна", "Фантастика", 8.1, 2021, "Warner Bros"));
        addFilm(new Film("Оппенгеймер", "Драма", 8.5, 2023, "Universal"));
        addFilm(new Film("Барби", "Комедия", 7.0, 2023, "Warner Bros"));
        addFilm(new Film("Человек-паук: Паутина вселенных", "Анимация", 8.6, 2023, "Sony"));
        addFilm(new Film("Бешеные псы", "Криминал", 8.3, 1992, "Miramax"));
        addFilm(new Film("Семь", "Триллер", 8.6, 1995, "New Line"));
        addFilm(new Film("Молчание ягнят", "Триллер", 8.6, 1991, "Orion"));
        addFilm(new Film("Ла-Ла Ленд", "Мюзикл", 8.0, 2016, "Summit Entertainment"));
        addFilm(new Film("Манчестер у моря", "Драма", 7.8, 2016, "Roadside Attractions"));
        addFilm(new Film("Лунный свет", "Драма", 7.4, 2016, "A24"));
        addFilm(new Film("Дэдпул", "Боевик", 8.0, 2016, "Fox"));
        addFilm(new Film("Зверополис", "Анимация", 8.0, 2016, "Walt Disney"));
    }
    
    private void addFilm(Film film) {
        films.add(film);
    }
    
    public List<Film> getAllFilms() {
        return new ArrayList<>(films);
    }
    
    public List<Film> filterFilms(Predicate<Film> predicate) {
        List<Film> result = new ArrayList<>();
        for (Film film : films) {
            if (predicate.test(film)) {
                result.add(film);
            }
        }
        return result;
    }
    
    public List<Film> filterFilms(List<Film> source, Predicate<Film> predicate) {
        List<Film> result = new ArrayList<>();
        for (Film film : source) {
            if (predicate.test(film)) {
                result.add(film);
            }
        }
        return result;
    }
    
    public List<Film> sortByRating(List<Film> filmList) {
        List<Film> sorted = new ArrayList<>(filmList);
        sorted.sort((f1, f2) -> Double.compare(f2.getRating(), f1.getRating()));
        return sorted;
    }
    
    public Set<String> getAllGenres() {
        Set<String> genres = new TreeSet<>();
        for (Film film : films) {
            genres.add(film.getGenre());
        }
        return genres;
    }
    
    public Set<String> getAllStudios() {
        Set<String> studios = new TreeSet<>();
        for (Film film : films) {
            studios.add(film.getStudio());
        }
        return studios;
    }
    
    public int getMinYear() {
        return films.stream().mapToInt(Film::getYear).min().orElse(1900);
    }
    
    public int getMaxYear() {
        return films.stream().mapToInt(Film::getYear).max().orElse(2024);
    }
}

class FilmLibraryView extends JFrame {
    private FilmLibraryController controller;
    
    JTable filmTable;
    FilmTableModel tableModel;
    JLabel statusLabel;
    JLabel totalCountLabel;
    JComboBox<String> genreCombo;
    JComboBox<String> studioCombo;
    JSpinner yearFromSpinner;
    JSpinner yearToSpinner;
    JSpinner ratingSpinner;
    JCheckBox sortByRatingChk;
    JCheckBox sortDescendingChk;
    
    public FilmLibraryView() {
        setupUI();
    }
    
    public void setController(FilmLibraryController controller) {
        this.controller = controller;
    }
    
    private void setupUI() {
        setTitle("Фильмотека - Управление коллекцией фильмов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 750);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(createFilterPanel(), BorderLayout.NORTH);
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        mainPanel.add(createStatusPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Фильтры и сортировка",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row1.add(new JLabel("Жанр:"));
        genreCombo = new JComboBox<>();
        row1.add(genreCombo);
        
        row1.add(Box.createHorizontalStrut(20));
        row1.add(new JLabel("Киностудия:"));
        studioCombo = new JComboBox<>();
        row1.add(studioCombo);
        
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row2.add(new JLabel("Год выпуска: от"));
        yearFromSpinner = new JSpinner();
        row2.add(yearFromSpinner);
        
        row2.add(new JLabel("до"));
        yearToSpinner = new JSpinner();
        row2.add(yearToSpinner);
        
        row2.add(Box.createHorizontalStrut(20));
        row2.add(new JLabel("Рейтинг ≥"));
        ratingSpinner = new JSpinner();
        row2.add(ratingSpinner);
        
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        sortByRatingChk = new JCheckBox("Сортировать по рейтингу");
        sortDescendingChk = new JCheckBox("По убыванию", true);
        row3.add(sortByRatingChk);
        row3.add(sortDescendingChk);
        
        row3.add(Box.createHorizontalStrut(20));
        
        JButton applyFilterBtn = new JButton("Применить фильтры");
        applyFilterBtn.setBackground(new Color(70, 130, 200));
        applyFilterBtn.setForeground(Color.BLACK);
        applyFilterBtn.setFont(new Font("Arial", Font.BOLD, 12));
        applyFilterBtn.addActionListener(e -> controller.applyFilters());
        row3.add(applyFilterBtn);
        
        JButton resetBtn = new JButton("Сбросить фильтры");
        resetBtn.setFont(new Font("Arial", Font.ITALIC, 12));
        resetBtn.addActionListener(e -> controller.resetFilters());
        row3.add(resetBtn);
        
        JPanel quickFiltersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        quickFiltersPanel.setBorder(BorderFactory.createTitledBorder("Быстрые фильтры"));
        
        JButton allFilmsBtn = new JButton("Все фильмы");
        allFilmsBtn.addActionListener(e -> controller.showAllFilms());
        quickFiltersPanel.add(allFilmsBtn);
        
        JButton warnerBtn = new JButton("Warner Bros");
        warnerBtn.addActionListener(e -> controller.showWarnerBrosFilms());
        quickFiltersPanel.add(warnerBtn);
        
        JButton highRatedBtn = new JButton("Рейтинг ≥ 9.0");
        highRatedBtn.addActionListener(e -> controller.showHighRatedFilms());
        quickFiltersPanel.add(highRatedBtn);
        
        JButton recentBtn = new JButton("Последние 10 лет");
        recentBtn.addActionListener(e -> controller.showRecentFilms());
        quickFiltersPanel.add(recentBtn);
        
        JButton top5Btn = new JButton("Топ 5 фильмов");
        top5Btn.addActionListener(e -> controller.showTop5Films());
        quickFiltersPanel.add(top5Btn);
        
        JButton dramaBtn = new JButton("Только драмы");
        dramaBtn.addActionListener(e -> controller.showDramas());
        quickFiltersPanel.add(dramaBtn);
        
        JButton scifiBtn = new JButton("Фантастика");
        scifiBtn.addActionListener(e -> controller.showSciFi());
        quickFiltersPanel.add(scifiBtn);
        
        JButton year2016Btn = new JButton("Фильмы 2016 года");
        year2016Btn.addActionListener(e -> controller.showYear2016Films());
        quickFiltersPanel.add(year2016Btn);
        
        filterPanel.add(row1);
        filterPanel.add(row2);
        filterPanel.add(row3);
        filterPanel.add(quickFiltersPanel);
        
        return filterPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Список фильмов"));
        
        tableModel = new FilmTableModel(new ArrayList<>());
        filmTable = new JTable(tableModel);
        
        filmTable.setRowHeight(25);
        filmTable.setFont(new Font("Arial", Font.PLAIN, 12));
        filmTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        filmTable.getTableHeader().setBackground(new Color(240, 240, 240));
        filmTable.setSelectionBackground(new Color(184, 207, 229));
        
        TableRowSorter<FilmTableModel> sorter = new TableRowSorter<>(tableModel);
        filmTable.setRowSorter(sorter);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer ratingRenderer = new DefaultTableCellRenderer() {
            private DecimalFormat df = new DecimalFormat("0.0");
            @Override
            protected void setValue(Object value) {
                if (value instanceof Double) {
                    double rating = (Double) value;
                    setText(df.format(rating));
                    if (rating >= 9.0) setForeground(new Color(0, 100, 0));
                    else if (rating >= 8.0) setForeground(new Color(0, 0, 139));
                    else if (rating >= 7.0) setForeground(new Color(255, 140, 0));
                    else setForeground(Color.RED);
                } else {
                    super.setValue(value);
                }
            }
        };
        ratingRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        filmTable.getColumnModel().getColumn(0).setPreferredWidth(280);
        filmTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        filmTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        filmTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        filmTable.getColumnModel().getColumn(2).setCellRenderer(ratingRenderer);
        filmTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        filmTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        filmTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        filmTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        JScrollPane scrollPane = new JScrollPane(filmTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        
        statusLabel = new JLabel(" Готов к работе");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(statusLabel, BorderLayout.WEST);
        
        totalCountLabel = new JLabel();
        totalCountLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(totalCountLabel, BorderLayout.EAST);
        
        return statusPanel;
    }
    
    public void updateFilmList(List<Film> films) {
        tableModel.updateData(films);
    }
    
    public void updateStatus(String message, int foundCount, int totalCount) {
        statusLabel.setText(" " + message + " | Найдено фильмов: " + foundCount);
        totalCountLabel.setText("Всего в базе: " + totalCount);
    }
    
    public void populateCombos(Set<String> genres, Set<String> studios) {
        genreCombo.removeAllItems();
        genreCombo.addItem("Все жанры");
        for (String genre : genres) {
            genreCombo.addItem(genre);
        }
        
        studioCombo.removeAllItems();
        studioCombo.addItem("Все студии");
        for (String studio : studios) {
            studioCombo.addItem(studio);
        }
    }
    
    public void setupSpinners(int minYear, int maxYear) {
        yearFromSpinner.setModel(new SpinnerNumberModel(minYear, minYear, maxYear, 1));
        yearToSpinner.setModel(new SpinnerNumberModel(maxYear, minYear, maxYear, 1));
        ratingSpinner.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
    }
    
    public void setGenre(String genre) {
        genreCombo.setSelectedItem(genre);
    }
    
    public void setStudio(String studio) {
        studioCombo.setSelectedItem(studio);
    }
    
    public void setYearRange(int from, int to) {
        yearFromSpinner.setValue(from);
        yearToSpinner.setValue(to);
    }
    
    public void setMinRating(double rating) {
        ratingSpinner.setValue(rating);
    }
    
    public void setSortByRating(boolean sort) {
        sortByRatingChk.setSelected(sort);
    }
    
    public void setSortDescending(boolean descending) {
        sortDescendingChk.setSelected(descending);
    }
    
    public void resetAllFilters() {
        genreCombo.setSelectedIndex(0);
        studioCombo.setSelectedIndex(0);
        
        SpinnerNumberModel fromModel = (SpinnerNumberModel) yearFromSpinner.getModel();
        SpinnerNumberModel toModel = (SpinnerNumberModel) yearToSpinner.getModel();
        
        yearFromSpinner.setValue(fromModel.getMinimum());
        yearToSpinner.setValue(toModel.getMaximum());
        
        ratingSpinner.setValue(0.0);
        sortByRatingChk.setSelected(false);
        sortDescendingChk.setSelected(true);
    }
    
    public String getSelectedGenre() {
        return (String) genreCombo.getSelectedItem();
    }
    
    public String getSelectedStudio() {
        return (String) studioCombo.getSelectedItem();
    }
    
    public int getYearFrom() {
        return (Integer) yearFromSpinner.getValue();
    }
    
    public int getYearTo() {
        return (Integer) yearToSpinner.getValue();
    }
    
    public double getMinRating() {
        return (Double) ratingSpinner.getValue();
    }
    
    public boolean isSortByRating() {
        return sortByRatingChk.isSelected();
    }
    
    public boolean isSortDescending() {
        return sortDescendingChk.isSelected();
    }
}

class FilmTableModel extends AbstractTableModel {
    private List<Film> films;
    private String[] columns = {"Название", "Год", "Рейтинг", "Жанр", "Киностудия"};
    
    public FilmTableModel(List<Film> films) {
        this.films = films;
    }
    
    public void updateData(List<Film> newFilms) {
        this.films = newFilms;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return films.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film film = films.get(rowIndex);
        switch (columnIndex) {
            case 0: return film.getTitle();
            case 1: return film.getYear();
            case 2: return film.getRating();
            case 3: return film.getGenre();
            case 4: return film.getStudio();
            default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Double.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return Object.class;
        }
    }
}

class FilmLibraryController {
    private FilmLibraryView view;
    private FilmLibraryModel model;
    
    public FilmLibraryController(FilmLibraryView view, FilmLibraryModel model) {
        this.view = view;
        this.model = model;
        
        view.setController(this);
        view.populateCombos(model.getAllGenres(), model.getAllStudios());
        view.setupSpinners(model.getMinYear(), model.getMaxYear());
        
        showAllFilms();
    }
    
    public void applyFilters() {
        List<Film> filtered = model.getAllFilms();
        
        String genre = view.getSelectedGenre();
        String studio = view.getSelectedStudio();
        int yearFrom = view.getYearFrom();
        int yearTo = view.getYearTo();
        double minRating = view.getMinRating();
        
        if (!genre.equals("Все жанры")) {
            filtered = model.filterFilms(filtered, f -> f.getGenre().equals(genre));
        }
        
        if (!studio.equals("Все студии")) {
            filtered = model.filterFilms(filtered, f -> f.getStudio().equals(studio));
        }
        
        filtered = model.filterFilms(filtered, f -> f.getYear() >= yearFrom && f.getYear() <= yearTo);
        filtered = model.filterFilms(filtered, f -> f.getRating() >= minRating);
        
        if (view.isSortByRating()) {
            filtered.sort((f1, f2) -> {
                int cmp = Double.compare(f2.getRating(), f1.getRating());
                return view.isSortDescending() ? cmp : -cmp;
            });
        }
        
        view.updateFilmList(filtered);
        view.updateStatus("Фильтры применены", filtered.size(), model.getAllFilms().size());
    }
    
    public void resetFilters() {
        view.resetAllFilters();
        showAllFilms();
        view.updateStatus("Фильтры сброшены", model.getAllFilms().size(), model.getAllFilms().size());
    }
    
    public void showAllFilms() {
        List<Film> allFilms = model.getAllFilms();
        view.updateFilmList(allFilms);
        view.updateStatus("Показаны все фильмы", allFilms.size(), allFilms.size());
        
        view.resetAllFilters();
    }
    
    public void showWarnerBrosFilms() {
        List<Film> warnerFilms = model.filterFilms(f -> f.getStudio().equals("Warner Bros"));
        warnerFilms = model.sortByRating(warnerFilms);
        view.updateFilmList(warnerFilms);
        view.updateStatus("Фильмы студии Warner Bros", warnerFilms.size(), model.getAllFilms().size());
        
        view.setGenre("Все жанры");
        view.setStudio("Warner Bros");
        view.setYearRange(model.getMinYear(), model.getMaxYear());
        view.setMinRating(0.0);
        view.setSortByRating(true);
        view.setSortDescending(true);
    }
    
    public void showHighRatedFilms() {
        List<Film> highRated = model.filterFilms(f -> f.getRating() >= 9.0);
        highRated = model.sortByRating(highRated);
        view.updateFilmList(highRated);
        view.updateStatus("Фильмы с рейтингом 9.0 и выше", highRated.size(), model.getAllFilms().size());
        
        view.setGenre("Все жанры");
        view.setStudio("Все студии");
        view.setYearRange(model.getMinYear(), model.getMaxYear());
        view.setMinRating(9.0);
        view.setSortByRating(true);
        view.setSortDescending(true);
    }
    
    public void showRecentFilms() {
        int currentYear = Year.now().getValue();
        int startYear = currentYear - 10;
        List<Film> recent = model.filterFilms(f -> f.getYear() >= startYear);
        recent = model.sortByRating(recent);
        view.updateFilmList(recent);
        view.updateStatus("Фильмы последних 10 лет", recent.size(), model.getAllFilms().size());
        
        view.setGenre("Все жанры");
        view.setStudio("Все студии");
        view.setYearRange(startYear, currentYear);
        view.setMinRating(0.0);
        view.setSortByRating(true);
        view.setSortDescending(true);
    }
    
    public void showTop5Films() {
        List<Film> allFilms = model.getAllFilms();
        List<Film> top5 = model.sortByRating(allFilms);
        top5 = top5.subList(0, Math.min(5, top5.size()));
        view.updateFilmList(top5);
        view.updateStatus("Топ 5 фильмов по рейтингу", top5.size(), model.getAllFilms().size());
        
        view.setGenre("Все жанры");
        view.setStudio("Все студии");
        view.setYearRange(model.getMinYear(), model.getMaxYear());
        view.setMinRating(0.0);
        view.setSortByRating(true);
        view.setSortDescending(true);
    }
    
    public void showDramas() {
        List<Film> dramas = model.filterFilms(f -> f.getGenre().equals("Драма"));
        if (view.isSortByRating()) {
            dramas = model.sortByRating(dramas);
        }
        view.updateFilmList(dramas);
        view.updateStatus("Драматические фильмы", dramas.size(), model.getAllFilms().size());
        
        view.setGenre("Драма");
        view.setStudio("Все студии");
        view.setYearRange(model.getMinYear(), model.getMaxYear());
        view.setMinRating(0.0);
        view.setSortByRating(view.isSortByRating());
        view.setSortDescending(view.isSortDescending());
    }
    
    public void showSciFi() {
        List<Film> scifi = model.filterFilms(f -> f.getGenre().equals("Фантастика"));
        if (view.isSortByRating()) {
            scifi = model.sortByRating(scifi);
        }
        view.updateFilmList(scifi);
        view.updateStatus("Фантастические фильмы", scifi.size(), model.getAllFilms().size());
        
        view.setGenre("Фантастика");
        view.setStudio("Все студии");
        view.setYearRange(model.getMinYear(), model.getMaxYear());
        view.setMinRating(0.0);
        view.setSortByRating(view.isSortByRating());
        view.setSortDescending(view.isSortDescending());
    }
    
    public void showYear2016Films() {
        List<Film> year2016Films = model.filterFilms(f -> f.getYear() == 2016);
        if (view.isSortByRating()) {
            year2016Films = model.sortByRating(year2016Films);
        }
        view.updateFilmList(year2016Films);
        view.updateStatus("Фильмы 2016 года", year2016Films.size(), model.getAllFilms().size());
        
        view.setGenre("Все жанры");
        view.setStudio("Все студии");
        view.setYearRange(2016, 2016);
        view.setMinRating(0.0);
        view.setSortByRating(view.isSortByRating());
        view.setSortDescending(view.isSortDescending());
    }
}

public class FilmLibraryMVC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            FilmLibraryModel model = new FilmLibraryModel();
            FilmLibraryView view = new FilmLibraryView();
            new FilmLibraryController(view, model);
            
            view.setVisible(true);
        });
    }
}