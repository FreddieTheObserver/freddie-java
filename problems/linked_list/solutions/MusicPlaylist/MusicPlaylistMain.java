package linked_list.solutions.MusicPlaylist;

public class MusicPlaylistMain {
    public static void main(String[] args) {
        // 1. Create a MusicPlaylist
        MusicPlaylist playlist = new MusicPlaylist();

        // 2. Add songs to the END
        playlist.addToEnd(1, "Blinding Lights", "The Weeknd", "Pop", 200, 5);
        playlist.addToEnd(2, "Bohemian Rhapsody", "Queen", "Rock", 354, 5);
        playlist.addToEnd(3, "So What", "Miles Davis", "Jazz", 545, 4);

        // 3. Print head song title
        System.out.println(playlist.head.song.getTitle());

        // 4. Print tail song title
        System.out.println(playlist.tail.song.getTitle());

        // 5. Add to START
        playlist.addToStart(4, "Lose Yourself", "Eminem", "Hip-Hop", 326, 5);

        // 6. Print head song title
        System.out.println(playlist.head.song.getTitle());

        // 7. Start playback
        playlist.play();

        // 8. next
        playlist.next();

        // 9. next
        playlist.next();

        // 10. previous
        playlist.previous();

        // 11. nowPlaying
        playlist.nowPlaying();

        // 12. Add after current
        playlist.addAfterCurrent(5, "Uptown Funk", "Bruno Mars", "Pop", 270, 4);

        // 13. next
        playlist.next();

        // 14. Search for artist "queen"
        Song found = playlist.searchByArtist("queen");
        System.out.println(found.getTitle());

        // 15. Search for artist "Nobody"
        Song notFound = playlist.searchByArtist("Nobody");
        System.out.println(notFound != null ? notFound.getTitle() : "Not found");

        // 16. Count songs with genre "Pop"
        System.out.println(playlist.countByGenre("Pop"));

        // 17. Count songs with genre "Jazz"
        System.out.println(playlist.countByGenre("Jazz"));

        // 18. Get total duration
        System.out.println(playlist.getTotalDuration());

        // 19. Remove song with ID 3
        System.out.println(playlist.removeById(3).getTitle());

        // 20. Remove from start
        System.out.println(playlist.removeFromStart().getTitle());

        // 21. Remove from end
        System.out.println(playlist.removeFromEnd().getTitle());

        // 22. Display playlist
        playlist.displayPlayList();

        // 23. Display reverse
        playlist.displayReverse();
    }
}

class Song {
    int id;
    String title;
    String artist;
    String genre;
    int duration;
    int rating;

    public Song (int id, String title, String artist, String genre, int duration, int rating) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0) {
            this.duration = duration;
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }
}

class Node {
    Song song;
    Node prev, next;

    public Node (Song song, Node prev, Node next) {
        this.song = song;
        this.prev = prev;
        this.next = next;
    }
}

class MusicPlaylist {
    Node head, tail, current;

    public MusicPlaylist() {
        head = tail = current = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToEnd(int id, String title, String artist, String genre, int duration, int rating) {
        Song newSong = new Song(id, title, artist, genre, duration, rating);

        if (isEmpty()) {
            head = tail = new Node(newSong, null, null);
        } else {
            tail.next = new Node(newSong, tail, null);
            tail = tail.next;
        }
    }

    public void addToStart(int id, String title, String artist, String genre, int duration, int rating) {
        Song newSong = new Song(id, title, artist, genre, duration, rating);

        head = new Node(newSong, null, head);
        if (head.next != null) {
            head.next.prev = head;
        } else {
            tail = head;
        }
    }

    public void addAfterCurrent(int id, String title, String artist, String genre, int duration, int rating) {
        if (current == null) {
            addToEnd(id, title, artist, genre, duration, rating);
        } else {
            Song newSong = new Song(id, title, artist, genre, duration, rating);
            Node newNode = new Node(newSong, current, current.next);
            if (current.next != null) {
                current.next.prev = newNode;
            } else {
                tail = newNode;
            }
            current.next = newNode;
        }
    }

    public Song removeFromStart() {
        Song song = null;

        if (isEmpty()) {
            return song;
        } else if (head == tail) {
            song = head.song;
            head = tail = null;
        } else {
            song = head.song;
            head = head.next;
            head.prev = null;
        }

        return song;
    }

    public Song removeFromEnd() {
        Song song = null;

        if (isEmpty()) {
            return song;
        } else if (head == tail) {
            song = head.song;
            head = tail = null;
        } else {
            song = tail.song;
            tail = tail.prev;
            tail.next = null;
        }

        return song;
    }

    public void play() {
        if (isEmpty()) {
            System.out.println("Playlist is empty");
        } else {
            current = head;
            System.out.println("Now playing: " + current.song.getTitle());
        }
    }

    public void next() {
        if (current.next == null) {
            System.out.println("End of playlist");
        } else {
            current = current.next;
            System.out.println("Now playing: " + current.song.getTitle());
        }
    }

    public void previous() {
        if (current.prev == null) {
            System.out.println("Start of playlist");
        } else {
            current = current.prev;
            System.out.println("Now playing: " + current.song.getTitle());
        }
    }

    public void nowPlaying() {
        if (current != null) {
            System.out.println(current.song.getTitle() + " - " + current.song.getArtist());
        } else {
            System.out.println("No song is currently playing");
        }
    }

    public Song searchByArtist(String artist) {
        if (isEmpty()) {
            return null;
        }

        Node temp = head;

        while (temp != null) {
            if (temp.song.getArtist().equalsIgnoreCase(artist)) {
                return temp.song;
            }
            temp = temp.next;
        }
        return null;
    }

    public int countByGenre(String genre) {
        int count = 0;

        Node temp = head;
        while (temp != null) {
            if (temp.song.getGenre().equalsIgnoreCase(genre)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    public Song removeById(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.song.getId() == id) {
                if (temp == current) {
                    current = (temp.next != null) ? temp.next : temp.prev;
                }

                if (temp == head) {
                    head = head.next;
                }

                if (temp == tail) {
                    tail = tail.prev;
                }

                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                }

                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                return temp.song;
            }
            temp = temp.next;
        }
        return null;
    }

    public int getTotalDuration() {
        Node temp = head;
        int second = 0;

        while (temp != null) {
            second  += temp.song.getDuration();
            temp = temp.next;
        }

        return second;
    }

    public void displayPlayList() {
        System.out.println("--- Playlist ---");
        Node temp = head;

        while (temp != null) {
            System.out.println("[" + temp.song.getId() + "] " + temp.song.getTitle() + " | " +
                            temp.song.getArtist() + " | " + temp.song.getGenre() + " | " + temp.song.getDuration() / 60 +
                            ":" + String.format("%02d", temp.song.getDuration() % 60) + " | " + temp.song.getRating() + " stars");
            temp = temp.next;
        }
    }

    public void displayReverse() {
        System.out.println("--- Playlist (Reverse) ---");
        Node temp = tail;

        while (temp != null) {
            System.out.println("[" + temp.song.getId() + "] " + temp.song.getTitle() + " | " +
                            temp.song.getArtist() + " | " + temp.song.getGenre() + " | " + temp.song.getDuration() / 60 +
                            ":" + String.format("%02d", temp.song.getDuration() % 60) + " | " + temp.song.getRating() + " stars");
            temp = temp.prev;
        }
    }
}

