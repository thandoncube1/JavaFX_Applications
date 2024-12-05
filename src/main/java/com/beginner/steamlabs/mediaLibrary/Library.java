package com.beginner.steamlabs.mediaLibrary;

import java.util.*;

public class Library {
    private static final ArrayList<Media> mediaCollection = new ArrayList<>();

    // Setup Game objects
    private static final Game fc24 = new Game();
    private static final Game squad = new Game();
    private static final Game cyberpunk = new Game();
    private static final Game halo = new Game();
    private static final Game forza = new Game();

    // Setup Movie objects
    private static final Movie deadPool = new Movie();
    private static final Movie duneP2 = new Movie();
    private static final Movie furiosaMadMax = new Movie();
    private static final Movie gladiator2 = new Movie();
    private static final Movie equalizer = new Movie();

    // Setup genres
    private static final Genre simulation = new Genre("Simulation");
    private static final Genre rpg = new Genre("RPG");
    private static final Genre action = new Genre("Action");
    private static final Genre mature = new Genre("Mature");
    private static final Genre adventure = new Genre("Adventure");
    private static final Genre fantasy = new Genre("Fantasy");
    private static final Genre drama = new Genre("Drama");
    private static final Genre crime = new Genre("Crime");
    private static final Genre thriller = new Genre("Thriller");
    private static final Genre sport = new Genre("Sports");
    private static final Genre comedy = new Genre("Comedy");
    // Set up the Static members
    static {
        // FC24
        fc24.setMediaTitle("EA SPORTS FC 24 Standard Edition");
        fc24.setDescription("EA SPORTS FC™ 24 welcomes you to The World’s Game: the most true-to-football experience ever with HyperMotionV, PlayStyles optimised by Opta, and an enhanced Frostbite™ Engine.");
        fc24.setGenres(new Genre[] { simulation });
        fc24.setFeatures(new String[] { "Multiplayer", "Co-op", "Single Player" });
        fc24.setImages(new String[] {"fc24-image-1", "fc24-image-2" });
        fc24.setAgeRating("ESRB Everyone");
        // Squad
        squad.setMediaTitle("Squad Commander Edition");
        squad.setDescription("Squad is a tactical FPS that provides authentic combat experiences through teamwork, communication, and realistic combat. It bridges the gap between arcade shooter and military realism with 100-player battles, combined-arms warfare, and base building.");
        squad.setGenres(new Genre[] { simulation, action, adventure });
        squad.setFeatures(new String[] { "Multiplayer", "Strategy" });
        squad.setImages(new String[] { "squad-image-1", "squad-image-2" });
        squad.setAgeRating("ESRB Mature");
        // Cyberpunk
        cyberpunk.setMediaTitle("Cyberpunk 2077 Ultimate Edition");
        cyberpunk.setDescription("Cyberpunk 2077 is an open-world, action-adventure RPG set in the dark future of Night City — a dangerous megalopolis obsessed with power, glamor, and ceaseless body modification.");
        cyberpunk.setGenres(new Genre[] { rpg, action, mature });
        cyberpunk.setFeatures(new String[] { "Simulation", "Singleplayer", "Stream Trading Cards" });
        cyberpunk.setImages(new String[] {"cyber-image-1", "cyber-image-2" });
        cyberpunk.setAgeRating("ESRB Mature 17+");
        // Halo
        halo.setMediaTitle("Halo Infinite (Campaign)");
        halo.setDescription("From one of gaming's most iconic sagas, Halo is bigger than ever. Featuring an expansive open-world campaign and a dynamic free to play multiplayer experience.");
        halo.setGenres(new Genre[] { action, simulation, fantasy });
        halo.setFeatures(new String[] { "Online PvP", "Cross-Platform Multiplayer", "LAN PvP" });
        halo.setImages(new String[] { "halo-image-1", "halo-image-2" });
        halo.setAgeRating("ESRB Teen");
        // Forza
        forza.setMediaTitle("Forza Motorsport NASCAR Series");
        forza.setDescription("Out-build the competition in Career. Race the globe in Multiplayer. Compete in over 500 cars on 27 world-famous tracks – now featuring the exhilarating Nürburgring Nordschleife!");
        forza.setGenres(new Genre[] { simulation, sport, action });
        forza.setFeatures(new String[] {"Singleplayer", "Online PvP", "Cross-Platform Multiplayer" });
        forza.setImages(new String[] { "forza-image-1", "forza-image-2" });
        forza.setAgeRating("ESRB Everyone");

        // Deadpool
        deadPool.setMediaTitle("Deadpool & Wolverine");
        deadPool.setGenres(new Genre[] { action, comedy, mature });
        deadPool.setDescription("In this multiverse story Deadpool is approached by a man named Paradox who intends on vaporizing his entire universe because apparently it's not supposed to go on living without the now deceased hero Wolverine. Deadpool kidnaps another version of Wolverine to replace but this only results in Paradox banishing both Deadpool and Wolverine.");
        deadPool.setActors(new String[] { "Ryan Reynolds", "Hugh Jackman", "Emma Corrin", "Wesley Snipes" });
        deadPool.setProduction(new String[] { "Marvel Studios", "20th Century Studios" });
        deadPool.setDuration("2h 8m");
        deadPool.setImages(new String[] {"deadpool", "deadpool-image-2"});
        deadPool.setInitialReleaseDate(new Date(2024, Calendar.JULY, 26));
        // duneP2
        duneP2.setMediaTitle("Dune: Part Two");
        duneP2.setDescription("Paul Atreides unites with the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he endeavors to prevent a terrible future.");
        duneP2.setGenres(new Genre[] { adventure, drama, action });
        duneP2.setActors(new String[] { "Timothee Chalamet", "Zendaya", "Rebecca Ferguson", "Christophher Walken" });
        duneP2.setProduction(new String[] { "Warner Bros.", "Legendary Entertainment" });
        duneP2.setDuration("2h 46m");
        duneP2.setImages(new String[] {"dune", "dune-image-2"});
        duneP2.setInitialReleaseDate(new Date(2024, Calendar.MARCH, 1));
        // MadMax
        furiosaMadMax.setMediaTitle("Furiosa: A Mad Max Saga");
        furiosaMadMax.setDescription("As the world fell, young Furiosa is snatched from the Green Place of Many Mothers and falls into the hands of a great Biker Horde led by the Warlord Dementus. Sweeping through the Wasteland they come across the Citadel presided over by The Immortan Joe. While the two Tyrants war for dominance, Furiosa must survive many trials as she puts together the means to find her way home.");
        furiosaMadMax.setGenres(new Genre[] { action, adventure, thriller });
        furiosaMadMax.setActors(new String[] { "Anya Taylor-Joy", "Chris Hemsworth", "Tom Burke", "Ayla Browne" });
        furiosaMadMax.setProduction(new String[] { "Warner Bros.", "Domain Entertainment (II)" });
        furiosaMadMax.setDuration("2h 28m");
        furiosaMadMax.setImages(new String[] { "madmax", "madmax-image-2" });
        furiosaMadMax.setInitialReleaseDate(new Date(2024, Calendar.MAY, 24));
        // Gladiator 2
        gladiator2.setMediaTitle("Gladiator II");
        gladiator2.setDescription("After his home is conquered by the tyrannical emperors who now lead Rome, Lucius is forced to enter the Colosseum and must look to his past to find strength to return the glory of Rome to its people.");
        gladiator2.setGenres(new Genre[] {action, drama, adventure});
        gladiator2.setActors(new String[] {"Paul Mescal", "Denzel Washington", "Pedro Pascal", "Connie Nielsen"});
        gladiator2.setProduction(new String[] {"Paramount Pictures", "Scott Free Productions"});
        gladiator2.setDuration("2h 28m");
        gladiator2.setImages(new String[] {"gladiator", "gladiator-image-2"});
        gladiator2.setInitialReleaseDate(new Date(2024, Calendar.NOVEMBER, 22));
        // Equalizer 2
        equalizer.setMediaTitle("The Equalizer 2");
        equalizer.setDescription("Director Antoine Fuqua reunites with Denzel Washington in this sequel to 2014's The Equalizer. Washington resumes his role as Robert McCall: a retired CIA Black Ops operative who now works as a Lyft driver and moonlights as a vigilante. When his long-time friend is murdered he embarks on a relentless, globe-trotting quest for vengeance.");
        equalizer.setGenres(new Genre[] {action, thriller, crime});
        equalizer.setActors(new String[] {"Denzel Washington", "Pedro Pascal", "Ashton Sanders", "Melissa Leo"});
        equalizer.setProduction(new String[] {"Columbia Pictures", "Escape Artists"});
        equalizer.setDuration("2h 1m");
        equalizer.setImages(new String[] {"equalizer", "equalizer-image-2"});
        equalizer.setInitialReleaseDate(new Date(2018, Calendar.JULY, 20));

        // Add elements into the array.
        mediaCollection.addAll(Arrays.asList(equalizer, halo, forza, duneP2, gladiator2, furiosaMadMax, cyberpunk, squad, deadPool, fc24));
    }

    public static ArrayList<Media> getLibraryCollection() {
        return mediaCollection;
    }

    public Library() {}
}
