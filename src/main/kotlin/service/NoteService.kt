package service

import wallNotes.Comment
import wallNotes.CommentNotFoundException
import wallNotes.Note
import wallNotes.NoteNotFoundException

object NoteService {
    var globalIdNote = 0
    var globalIdComment = 0

    private val listNotes = mutableListOf<Note>()
    private val listComments = mutableListOf<Comment>()

    private val deletedListNotes = mutableListOf<Note>()
    private val deletedListComments = mutableListOf<Comment>()

    fun addNotes(note: Note) {
        globalIdNote++
        val copyNote = note.copy(noteId = globalIdNote)
        listNotes.add(copyNote)
    }

    fun deleteCommentsByNoteId(noteId: Int) {
        for (comment: Comment in getComments()) {
            if (noteId == comment.noteId) {
                deletedListComments.add(comment)
                listComments.remove(comment)
                return
            }
        }
    }

    fun deletedNote(noteId: Int) {
        for (note: Note in getNotes()) {
            if (noteId == note.noteId) {
                deleteCommentsByNoteId(noteId)
                deletedListNotes.add(note)
                listNotes.remove(note)
                return
            }
        }
        throw NoteNotFoundException()
    }

    fun editNote(noteId: Int, editText: Note) {
        for (note: Note in getNotes()) {
            if (noteId == note.noteId) {
                note.noteText = editText.noteText
                return
            }
        }
    }

    fun getByIdNote(noteId: Int): Note {
        for (note: Note in getNotes()) {
            if (noteId == note.noteId) {
                return note
            }
        }
        throw NoteNotFoundException()
    }

    fun getNotes(): MutableList<Note> {
        return listNotes
    }

    fun createComment(noteId: Int, comment: Comment) {
        for (note: Note in getNotes()) {
            if (noteId == note.noteId) {
                globalIdComment++
                val copyComment = comment.copy(commentId = globalIdComment, noteId = note.noteId)
                listComments.add(copyComment)
                return
            }
        }
        throw CommentNotFoundException()
    }

    fun deleteComment(commentId: Int) {
        for (comment: Comment in getComments()) {
            if (commentId == comment.commentId) {
                deletedListComments.add(comment)
                listComments.remove(comment)
                return
            }
        }
    }

    fun editComment(commentId: Int, editcomment: Comment) {
        for (comment: Comment in getComments()) {
            if (commentId == comment.commentId) {
                comment.commentText = editcomment.commentText
                return
            }
        }
       throw CommentNotFoundException()
    }

    fun getCommentsByNote(noteId: Int): List<Comment>{
        val listComment = mutableListOf<Comment>()
        for (comment: Comment in listComments) {
            if (noteId == comment.noteId) {
                listComment.add(comment)
            }
        }
        return listComment
    }

    fun restoreComment(commentId: Int){
        for(comment : Comment in deletedListComments){
            if(commentId == comment.commentId){
                deletedListComments.remove(comment)
                listComments.add(comment)
                return
            }
        }
        throw CommentNotFoundException()
    }

    fun getComments(): MutableList<Comment> {
        return listComments
    }
}