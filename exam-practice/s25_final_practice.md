public int calculateTotalSize(Directory d) {
  // visit the current directory: add up the sizes of its files
  int sizeOfFiles = 0;
  for (File f : d.getFiles) sizeOfFiles += f.getSize();
  // files.stream().mapToInt(File::getSize).sum();
  // recurse on the children
  for (Directory dc : d.getSubdirectories()) {
    sizeOfFiles += calculateTotalSize(dc);
  }
  return sizeOfFiles;
}



public static <E> List<E> removeDuplicates(List<E> inputList) {
  Set<E> s = new HashSet<>(inputList);
  List<E> toReturn = new LinkedList<>(s);
  return toReturn;
}




public static <E> List<E> removeDuplicates(List<E> inputList) {
  Set<E> alreadySeen = new TreeSet<>();
  List<E> toReturn = new LinkedList<>();
  for (E elt : inputList) {
     boolean as = !alreadySeen.add(elt);
     if (!as) toReturn.add(elt);
  }
}



public static void countVotes(List<String> voteList) {
  Map<String, Integer> voteCount = new TreeMap<>();
  for (String s : voteList) {
    voteCount.compute(s, (k, v) -> v == null ? 1 : v + 1);
  }
}




















