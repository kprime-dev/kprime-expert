package it.unibz.cs.semint.kprime.expert

import it.unibz.cs.semint.kprime.expert.adapter.DocParser
import org.junit.Test
import kotlin.test.assertEquals

class DocParserTest {
    @Test
    fun test_parse_params() {
        // given
        val text = """
            ? quanto costa : prezzo
            asdasdasd
            >1
        """.trimIndent()
        // when
        val params = DocParser().parseAnswers(text)
        // then
        assertEquals("""
            {prezzo=1}
        """.trimIndent(),params.toString())
    }
}