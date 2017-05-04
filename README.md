Design:

FileParser: Convert the JSON file to an <appropriate data-structure> of Entry objects.

Lead: A lead entry consists of:
_id, email, firstName, lastName, address, entryDate

DeDup: DeDup takes a data-structure of Entry objects, and returns the same data-structure, de-duplicated
* Duplicate IDs and emails count as Dups; in this case, take the one with the newest date.
  * But if the dates are also identical, take the one that comes last in the list.

* When de-duplicating, log the changes: e.g.
    _id:   asdfjkl -> qwerty
    email: example@example.com
    firstName: NoChange
    lastName: McGee -> McGuire
    ...
Log: Take two entries as arguments (a 'from' and a 'to'). Log the differences between them.

FileOutput: Convert a data-structure of Entry objects back to a file.

main(args): Main takes only the filename to deduplicate from the command line. No other parameters?

Ideas:
Can we perform the deduplication using a HashSet data structure? HashSet uses equals, and hashCode methods
to perform comparisons (not Comparable/compareTo, but maybe we'll need that too). We would need to override these in
the Entry class in order for the set deduplication to be performed correctly. Is this a reasonable approach?

The <appropriate data structure> might be an Array, or can it be a HashSet from the get-go. FileParser should probably
convert it to a simple array of Entries. Single-responsibility says that DeDup should probably take that array,
push it into the HashSet, and return the deduped Array. Question: Does the order of the returned entries matter?
Is the extra memory required by the HashSet allowed?

Cannot use HashSet because a single hashCode/equals method cannot differentiate a Lead with the same email but
different id, and vice-versa. Instead, use a HashMap with key = String which can be either email or id. May need
to make two passes through the HashMap. Once to deduplicate same ids, and once to deduplicate same emails.

Questions:
After parsing the file into an array of Entries, can we perform the deduplication using an additional HashSet data
structure? HashSet uses equals, and hashCode methods to perform comparisons. We would need to override these in the
Entry class (using the defined rules) in order for the set deduplication to be performed correctly.
Is this a reasonable approach?

Does the order of the returned entries matter?