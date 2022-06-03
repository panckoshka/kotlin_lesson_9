package service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import wallNotes.Comment
import wallNotes.CommentNotFoundException
import wallNotes.Note
import wallNotes.NoteNotFoundException
import kotlin.test.assertFailsWith

internal class NoteServiceTest {

    @BeforeEach
    fun cleanUp() {
        val service = NoteService
        service.getNotes().clear()
        service.getComments().clear()
        service.globalIdNote = 0
        service.globalIdComment = 0
    }

    @Test
    fun addNotes() {
        val service = NoteService
        service.addNotes(
            Note(
                noteText = "qqq"
            )
        )
        service.addNotes(
            Note(
                noteText = "www"
            )
        )
        val note = service.getNotes()

        assertEquals(2, note.size)
    }

    @Test
    fun deletedNoteAndCommentAndRestoreComment() {
        val service = NoteService
        service.addNotes(
            Note(
                noteText = "qqq"
            )
        )
        service.addNotes(
            Note(
                noteText = "www"
            )
        )

        service.createComment(1, Comment(commentText = "text1"))
        service.createComment(2, Comment(commentText = "text2"))

        service.deletedNote(1)
        service.deleteComment(2)
        service.restoreComment(2)

        val note = service.getNotes()
        val comment = service.getComments()
        assertEquals(1, note.size)
        assertEquals(1, comment.size)

        assertFailsWith<NoteNotFoundException> {
            service.deletedNote(22)
        }
        assertFailsWith<CommentNotFoundException> {
            service.editComment(22, Comment(commentText = "xxx"))
        }
        assertFailsWith<CommentNotFoundException> {
            service.restoreComment(44)
        }
    }

    @Test
    fun editNoteAndEditComment() {
        val service = NoteService
        service.addNotes(
            Note(
                noteText = "qqq"
            )
        )
        service.addNotes(
            Note(
                noteText = "www"
            )
        )
        service.createComment(1, Comment(commentText = "text1"))
        service.createComment(2, Comment(commentText = "text2"))

        service.editNote(1, Note(noteText = "editText1"))
        service.editComment(1, Comment(commentText = "editText1"))

        val note = service.getNotes()
        val comment = service.getComments()

        assertEquals("editText1", note[0].noteText)
        assertEquals("editText1", comment[0].commentText)
    }

    @Test
    fun getByIdNoteAndComment() {
        val service = NoteService
        service.addNotes(
            Note(
                noteText = "qqq"
            )
        )
        service.addNotes(
            Note(
                noteText = "www"
            )
        )
        service.createComment(1, Comment(commentText = "text1"))
        service.createComment(2, Comment(commentText = "text2"))

        service.getByIdNote(2)
        service.getByIdNote(1)
        service.getCommentsByNote(1)

        val note = service.getNotes()
        val comment = service.getComments()

        assertEquals("www", note[1].noteText)
        assertEquals(1, note[0].noteId)
        assertEquals("text1", comment[0].commentText)
    }
}