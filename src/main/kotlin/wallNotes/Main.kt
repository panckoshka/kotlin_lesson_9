package wallNotes

import service.NoteService


fun main() {
    val service = NoteService

    service.addNotes(Note(noteText = "xxx"))
    service.addNotes(Note(noteText = "qqq"))
    service.addNotes(Note(noteText = "www"))
    service.addNotes(Note(noteText = "eee"))

    println(service.getNotes())

    service.createComment(1, Comment(commentText = "text1"))
    service.createComment(2, Comment(commentText = "text2"))
    service.createComment(2, Comment(commentText = "text22"))
    service.createComment(3, Comment(commentText = "text3"))
    service.createComment(4, Comment(commentText = "Text4"))

    println(service.getComments())

    val note1 = service.deletedNote(1)
    println(service.getNotes().size)
    println(service.getComments().size)

    val comment1 = service.deleteComment(4)
    println(service.getNotes())
    println(service.getComments())
    println()

    val note2 = service.editNote(2, Note(noteText = "editText"))
    println(service.getNotes())
    println()

    val comment2 = service.editComment( 2, Comment(commentText = "editText"))
    println(service.getComments())

    val note3 = service.getByIdNote(2)
    println(note3)
    println()

    val note4 = service.getCommentsByNote(2)
    println(note4)
    println()


    println(service.getComments())
    val comment3 = service.restoreComment(4)
    println(service.getComments())


//    val a = mutableListOf<Int>()
//    a.add(1)
//    a.add(2)
//
//    for (x in a) {
//        println(x)
//    }
//
//    for (i in a.indices) {
//        a[i] = a[i] * 10
//        println(i)
//    }
//
//    println(a)
}


