# CLAUDE.md

This file gives AI assistants the context needed to work productively in this repository.

## Repository overview

This is a personal learning/practice workspace for the **Groovy** language, created during EPAM-style training. It is a loose collection of standalone `.groovy` scripts — not an application, library, or Gradle/Maven project. There is no build system, no dependency manifest, no tests, and no package structure: each file is meant to be run on its own.

The scripts fall into two groups:

| Directory | Theme |
|---|---|
| `src/` | Closures fundamentals and a couple of small `java.io.File` exercises. |
| `files_scripts/src/` | Larger exercises: file I/O, date generation/parsing, XML (MarkupBuilder / XmlParser / XmlSlurper), JSON over HTTP, domain-object generation, CSV. |

### `src/` — closure and file basics

- `clousures.groovy` — bubble sort written as a closure over an `int[]`.
- `clousures2.groovy` — closure calling another closure (square of a number).
- `clousure3.groovy` — closure that takes another closure as a parameter.
- `clousure4.groovy` — applies a square closure to every element via a `for` loop.
- `clousures5.groovy` — same as `clousure4` but using `.each`.
- `clousures6.groovy` — closure that returns whichever of two numbers has the larger ones digit.
- `clFileLength.groovy` — closure that prints the length of a file by name.
- `fileList.groovy` — lists files in a directory via `dir.eachFile(FileType.FILES)`.
- `dirList.groovy` — lists subdirectories recursively via `dir.eachFileRecurse(FileType.DIRECTORIES)`.

Note the consistent typo **`clousure`** (should be `closure`) in filenames and identifiers. Preserve the existing spelling when editing unless explicitly asked to rename — there is no rename-all convention in place.

### `files_scripts/src/` — file/XML/JSON exercises

- `files1.groovy` — creates 10 `fileN.txt` files filled with 100,000 random integers each.
- `matrix.groovy` — reads those 10 files line-by-line via `BufferedReader` and builds a CSV `result.csv` with one value per column.
- `StringTotal.groovy` — reads `result.csv`, appends the row sum as an extra column, and atomically replaces the original file.
- `1000dates.groovy` — generates 1000 random dates between `0000-01-01` and `2020-01-01`.
- `1000datesNew.groovy` — same, but also appends a random time, then converts CET → GMT into `1000datesGMT.txt` with the compact `yyyyMMdd'T'HHmm` format.
- `transformDatesFile.groovy` — reformats an existing dates file from `yyyy-MM-dd` to `dd.MM.yyyy` using a temp file + rename.
- `parallelogram1.groovy` — defines a `Para` POGO, generates 1000 instances, filters by `length > 400` and half-perimeter, prints the index of the max-area element.
- `squareWithID.groovy` — same idea but with a `Square` POGO that carries a UUID and is stored in a `HashMap`.
- `xmlSquare.groovy` — emits the `Square` map as XML in two styles: manual `StringBuilder` and `groovy.xml.MarkupBuilder`. Defines its own inline `Square` POGO so it is self-contained.
- `USA_names1.groovy` — reads `USA_Names_Boys.txt`, `USA_Names_Girls.txt`, `surnames.txt`, and builds `usa_employees.xml` with 1000 employee entries using `MarkupBuilder`. Skips header lines via an `eachLine { it, line -> if (line > 1) ... }` pattern and extracts names with the regex `(?<=\t)[A-Z]\w+`.
- `countOfNames.groovy` — parses `usa_employees.xml` with `XmlSlurper` and writes first/last name frequency CSVs.
- `cnn.groovy` — prompts for a word, fetches the CNN World RSS via `XmlParser`, counts occurrences in each item's description (after stripping `<img … />`), and writes matched `pubDate`s plus a de-duplicated set of dates into files.
- `Customers.groovy` — `HttpURLConnection` GET against the OData Northwind demo service, parses JSON with `JsonSlurper`, and writes all `CustomerID`s to `customers.txt`.

## Language, runtime, and tooling

- All code is **Apache Groovy** (no specified version pinned anywhere in the repo — assume a reasonably modern Groovy 2.x/3.x/4.x that still ships `groovy.xml.MarkupBuilder`, `groovy.xml.XmlParser`, `groovy.xml.XmlSlurper`, `groovy.io.FileType`, and `groovy.json.JsonSlurper`).
- There is **no build file**. Scripts are meant to be run directly:
  ```
  groovy src/clousures.groovy
  groovy files_scripts/src/files1.groovy
  ```
- There is **no test suite**, no CI, and no linter configuration. Verification is by running the script and inspecting its output files.

## Conventions you must follow

These conventions are observed consistently across the existing files. Match them when adding or editing code.

### 1. Comments are in Russian
Every script has dense `//`-style comments in Russian explaining intent line by line. When you add or modify code, **write new comments in Russian** to stay consistent with the file. Do not translate existing comments to English unless asked. Do not delete existing comments.

### 2. Hardcoded Windows paths
Every `files_scripts/src/*.groovy` script begins with:
```groovy
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
```
This is deliberate — it is the author's local working directory on Windows. Preserve this exact path when editing unless the user explicitly asks to parameterize it. Do **not** convert these to POSIX paths, `File.separator`, or CLI arguments as a "cleanup" — that is a behavior change that breaks the scripts the author actually runs. On Linux (including this sandbox) the scripts cannot execute without modification; that is expected.

### 3. Line separator
Scripts use `def separator = System.getProperty("line.separator")` and concatenate it when building output files. Keep this idiom — don't swap in `'\n'` or `System.lineSeparator()`.

### 4. String building
Output is built with `StringBuilder` via the `<<` operator and then written once with `file << sb.toString()` (or just `file << sb`). Keep this pattern; don't rewrite to `PrintWriter` / `Files.write` / streams.

### 5. Two-file rename idiom for in-place edits
When rewriting an existing file, scripts write a `*_tmp` file, `delete()` the source, and `renameTo(path + "<original>")`. See `StringTotal.groovy` and `transformDatesFile.groovy`. Reuse this idiom instead of overwriting directly.

### 6. POGO classes
Domain types are tiny Groovy classes with public fields (no constructors, no getters/setters), instantiated with named args:
```groovy
class Square { int length; int width; String uuid }
new Square(length: rand(1000), width: rand(1000), uuid: uuid)
```
Preserve this style; don't add Lombok-ish accessors, `@Canonical`, or `final` fields unless asked.

### 7. Randomness
Two patterns coexist and both should be left as-is where they appear:
- `static int rand(int x) { (int)(1 + Math.random() * x) }` for 1..x integers.
- `new Random().nextInt(...)` for collection indices, booleans, and date offsets.

### 8. Filenames
Keep the existing **`clousure`** misspelling in `src/`. Numbered suffixes (`clousures`, `clousures2`, … `clousure6`) are intentional: each file is a separate exercise, not a refactor of the previous one.

## Editing guidance for assistants

- **Don't modernize.** No Gradle wrapper, no package declarations, no `@CompileStatic`, no `try-with-resources` retrofits (except where already present, e.g. `Customers.groovy`), no logging framework, no extracted utility modules. The point of the repo is hand-written exercises.
- **Don't create files that weren't asked for.** In particular, don't add a README, `build.gradle`, `.editorconfig`, `.gitignore`, or wrapper scripts unless the user explicitly requests them.
- **Don't fix latent bugs by default.** Surface real issues to the user and wait for direction before changing behavior. For example, `cnn.groovy` hits a live HTTP endpoint and will fail offline — that is a runtime dependency, not a bug to "fix" by caching or mocking.
- **Read before editing.** Many files look similar (five `clousure*` variants, two date-generators, two parallelogram scripts). Always open the specific file the user is asking about rather than inferring from its siblings.
- **Preserve generated-file names.** Downstream scripts read `result.csv`, `1000dates.txt`, `usa_employees.xml`, etc. by exact name. Renaming an output file in one script silently breaks another.

## Development workflow

- Branch: development happens on feature branches; the current working branch is set by the task.
- Commits: history shows short, task-oriented messages, often written in Russian and often referencing "задача N" (exercise N). Match that style — small commits, one exercise per commit, descriptive subject line.
- No PR template, no required reviews, no CI checks to satisfy.

## What this repo is *not*

- Not a reusable Groovy library.
- Not production code (paths, error handling, and encoding assumptions all reflect that).
- Not under active refactoring — treat each script as a finished artifact of a training exercise unless told otherwise.
