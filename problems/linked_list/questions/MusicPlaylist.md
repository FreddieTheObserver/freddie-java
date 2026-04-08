# Music Playlist Manager — Doubly Linked List

> **Solution:** [../solutions/MusicPlaylist/](../solutions/MusicPlaylist/)

## Problem Description

A music streaming app needs a playlist system that lets users navigate songs forward and backward, add/remove tracks, and perform playlist operations. You will implement this using a **Doubly Linked List**.

---

## Part 1: Define the `Song` class

Create a `Song` class with the following fields:

| Field       | Type     | Description                              |
|-------------|----------|------------------------------------------|
| `id`        | `int`    | Unique song ID                           |
| `title`     | `String` | Song title                               |
| `artist`    | `String` | Artist name                              |
| `genre`     | `String` | e.g. "Pop", "Rock", "Jazz", "Hip-Hop"   |
| `duration`  | `int`    | Duration in seconds                      |
| `rating`    | `int`    | 1 to 5 stars                             |

- Write a constructor that accepts all fields.
- Write getters and setters for each field.
- Validate that `rating` is between 1 and 5 in the setter.
- Validate that `duration` is positive in the setter.

---

## Part 2: Define the `Node` class

Create a `Node` class that wraps a `Song` object. Since this is a **doubly linked list**, each node must have both a `next` and a `prev` pointer.

---

## Part 3: Implement `MusicPlaylist`

Create a `MusicPlaylist` class with `head`, `tail`, and `current` pointers. The `current` pointer tracks which song is currently playing. Implement the following methods:

### Basic Operations

| Method | Description |
|--------|-------------|
| `isEmpty()` | Return `true` if the playlist is empty |
| `addToEnd(int id, String title, String artist, String genre, int duration, int rating)` | Add a song to the end of the playlist |
| `addToStart(int id, String title, String artist, String genre, int duration, int rating)` | Add a song to the beginning of the playlist |
| `addAfterCurrent(int id, String title, String artist, String genre, int duration, int rating)` | Insert a song right after the currently playing song. If nothing is playing, add to the end |
| `removeFromStart()` | Remove and return the first `Song` |
| `removeFromEnd()` | Remove and return the last `Song` |

### Playback Operations

| Method | Description |
|--------|-------------|
| `play()` | Start playing from the head. Set `current` to `head`. Print the song title. If empty, print "Playlist is empty" |
| `next()` | Move `current` to the next song and print its title. If at the end, print "End of playlist" and don't move |
| `previous()` | Move `current` to the previous song and print its title. If at the start, print "Start of playlist" and don't move |
| `nowPlaying()` | Print the currently playing song's title and artist, or "Nothing is playing" |

### Playlist-Specific Operations

| Method | Description |
|--------|-------------|
| `searchByArtist(String artist)` | Find and return the **first** `Song` by that artist (case-insensitive). Return `null` if not found |
| `countByGenre(String genre)` | Count how many songs match the given genre (case-insensitive) |
| `removeById(int id)` | Remove a song by its ID from anywhere in the playlist. If the removed song is `current`, move `current` to the next song (or previous if it was the last). Return the removed `Song`, or `null` if not found |
| `getTotalDuration()` | Return the total duration of all songs in seconds |
| `displayPlaylist()` | Print all songs from head to tail |
| `displayReverse()` | Print all songs from tail to head |

---

## Part 4: Test in `MusicPlaylistMain.java`

Write a `main` method that does the following (in order):

```
1. Create a MusicPlaylist

2. Add these songs to the END:
   - (1, "Blinding Lights", "The Weeknd",    "Pop",     200, 5)
   - (2, "Bohemian Rhapsody", "Queen",        "Rock",    354, 5)
   - (3, "So What",        "Miles Davis",     "Jazz",    545, 4)

3. Print head song title                    → expected: Blinding Lights
4. Print tail song title                    → expected: So What

5. Add to START:
   - (4, "Lose Yourself", "Eminem",          "Hip-Hop", 326, 5)
   Playlist: Lose Yourself <-> Blinding Lights <-> Bohemian Rhapsody <-> So What

6. Print head song title                    → expected: Lose Yourself

7. Start playback (play)                    → expected prints: Lose Yourself
8. next()                                   → expected prints: Blinding Lights
9. next()                                   → expected prints: Bohemian Rhapsody
10. previous()                              → expected prints: Blinding Lights
11. nowPlaying()                            → expected prints: Blinding Lights - The Weeknd

12. Add after current:
    - (5, "Uptown Funk", "Bruno Mars",       "Pop",     270, 4)
    Playlist: Lose Yourself <-> Blinding Lights <-> Uptown Funk <-> Bohemian Rhapsody <-> So What

13. next()                                  → expected prints: Uptown Funk

14. Search for artist "queen" (case-insensitive)
    Print the song title                    → expected: Bohemian Rhapsody

15. Search for artist "Nobody"
    Print result                            → expected: null (not found)

16. Count songs with genre "Pop"
    Print count                             → expected: 2 (Blinding Lights, Uptown Funk)

17. Count songs with genre "Jazz"
    Print count                             → expected: 1 (So What)

18. Get total duration
    Print total                             → expected: 1695

19. Remove song with ID 3 (So What)
    Print removed song's title              → expected: So What
    Playlist: Lose Yourself <-> Blinding Lights <-> Uptown Funk <-> Bohemian Rhapsody

20. Remove from start
    Print removed song's title              → expected: Lose Yourself

21. Remove from end
    Print removed song's title              → expected: Bohemian Rhapsody
    Playlist: Blinding Lights <-> Uptown Funk

22. Display the final playlist (head to tail)

23. Display the final playlist in reverse (tail to head)
```

---

## Expected Output

```
Blinding Lights
So What
Lose Yourself
Now playing: Lose Yourself
Now playing: Blinding Lights
Now playing: Bohemian Rhapsody
Now playing: Blinding Lights
Blinding Lights - The Weeknd
Now playing: Uptown Funk
Bohemian Rhapsody
Not found
2
1
1695
So What
Lose Yourself
Bohemian Rhapsody
--- Playlist ---
[1] Blinding Lights | The Weeknd | Pop | 3:20 | 5 stars
[5] Uptown Funk | Bruno Mars | Pop | 4:30 | 4 stars
--- Playlist (Reverse) ---
[5] Uptown Funk | Bruno Mars | Pop | 4:30 | 4 stars
[1] Blinding Lights | The Weeknd | Pop | 3:20 | 5 stars
```

---

## Bonus Challenges (Optional)

1. **`shuffle()`** — Randomize the order of songs in the playlist. Do not create a new list — rearrange the existing nodes.

2. **`moveToFront(int id)`** — Move a song to the front of the playlist by its ID without creating a new node.

3. **`removeDuplicateArtists()`** — If an artist appears more than once, keep only their highest-rated song and remove the rest.

4. **`reverse()`** — Reverse the entire playlist in-place by swapping `next` and `prev` pointers.

5. **`size()`** — Return the number of songs in the playlist.
