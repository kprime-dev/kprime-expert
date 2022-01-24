package it.unibz.cs.semint.kprime.expert

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class JacksonMapperTest {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonTypeName("person")
    class Person{
        var name:String = ""
        var surname:String = ""
    }

    class Archive {

        @JacksonXmlElementWrapper(useWrapping = false, localName = "employee")
        val people:MutableList<Person> = mutableListOf()

        @JacksonXmlProperty(localName = "myage")
        val age: Int = 2
    }


    @Test
    fun test_list_elements_name() {
        // given
        val people = mutableListOf<Person>()
        val gino = Person().apply {
            this.name = "gino"
            this.surname = "zino"
        }
        people.add(gino)
        val rino = Person().apply {
            this.name = "rino"
            this.surname = "zino"
        }
        people.add(rino)

        val archive = Archive()
        archive.people.addAll(people)

        val objectMapper = XmlMapper()
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        objectMapper.registerModule(JaxbAnnotationModule())

        // when
        val result = objectMapper.writeValueAsString(archive)
        // then
        assertEquals("""
            <Archive>
              <people>
                <person>
                  <name>gino</name>
                  <surname>zino</surname>
                </person>
              </people>
              <people>
                <person>
                  <name>rino</name>
                  <surname>zino</surname>
                </person>
              </people>
              <myage>2</myage>
            </Archive>

        """.trimIndent(),result)
    }

/*
    @JacksonXmlRootElement(localName = "person")
    class Person() {
        @JacksonXmlProperty
        val name:String  = ""
        @JacksonXmlProperty
        val surname:String  = ""
    }
    @JacksonXmlRootElement(localName = "Archive")
    class Archive() {
        @JacksonXmlElementWrapper(localName = "persons")
        val persons: MutableList<Person> = mutableListOf()
    }
*/

    @Test
    fun test_parse_list_with_singular_names() {
        // given
        val list = """
            <Archive>
              <people>
                <person>
                  <name>gino</name>
                  <surname>zino</surname>
                </person>
              </people>
              <people>
                <person>
                  <name>rino</name>
                  <surname>zino</surname>
                </person>
              </people>
              <myage>2</myage>
            </Archive>
 
        """.trimIndent()
        val objectMapper = XmlMapper()
        // when
        val archive = objectMapper.readValue<Archive>(list)
        // then
        assertEquals(2,archive.people.size)
    }

    @Test
    @Ignore
    fun test_parse_list_with_plural_names() {
        // given
        val list = """
            <Archive>
              <people>
                <persons>
                  <name>gino</name>
                  <surname>zino</surname>
                </persons>
              </people>
              <people>
                <persons>
                  <name>rino</name>
                  <surname>zino</surname>
                </persons>
              </people>
              <myage>2</myage>
            </Archive>

        """.trimIndent()
        val objectMapper = XmlMapper()
        // when
        val archive = objectMapper.readValue<Archive>(list)
        // then
        assertEquals(2,archive.people.size)
    }

    @Test
    fun test_purchase_serialize() {
        // given

        class Item() {
            var itemID:String = ""
            var itemPrice:Double = 0.0
        }
        class PurchaseOrder() {
            var itemOrders = mutableListOf<Item>()
        }


        val objectMapper= XmlMapper().apply {
            this.enable(SerializationFeature.INDENT_OUTPUT)
        }
        val purchaseOrder = PurchaseOrder()
        purchaseOrder.itemOrders.add(Item().apply {
            this.itemID = "aaa111";
            this.itemPrice = 34.22
        })
        purchaseOrder.itemOrders.add(Item().apply {
            this.itemID = "bbb222";
            this.itemPrice = 2.89
        })
        // when
        val purchaseOrderXml = objectMapper.writeValueAsString(purchaseOrder)
        // then
        assertEquals("""
            <PurchaseOrder>
              <itemOrders>
                <itemOrders>
                  <itemID>aaa111</itemID>
                  <itemPrice>34.22</itemPrice>
                </itemOrders>
                <itemOrders>
                  <itemID>bbb222</itemID>
                  <itemPrice>2.89</itemPrice>
                </itemOrders>
              </itemOrders>
            </PurchaseOrder>
            
        """.trimIndent(),purchaseOrderXml)
    }

    /*
<PurchaseOrder xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <ItemsOrders>
        <Item>
            <ItemID>aaa111</ItemID>
            <ItemPrice>34.22</ItemPrice>
        </Item>
        <Item>
            <ItemID>bbb222</ItemID>
            <ItemPrice>2.89</ItemPrice>
        </Item>
    </ItemsOrders>
</PurchaseOrder>

     */

    @Test
    fun test_purchase_serialize2() {
        // given

        class Item() {
            var itemID:String = ""
            var itemPrice:Double = 0.0
        }
        class PurchaseOrder() {
            @JacksonXmlElementWrapper(localName = "ItemOrders")
            @JacksonXmlProperty(localName = "Item")
            var itemsOrders = mutableListOf<Item>()
        }

        val objectMapper= XmlMapper().apply {
            this.enable(SerializationFeature.INDENT_OUTPUT)
        }
        val purchaseOrder = PurchaseOrder()
        purchaseOrder.itemsOrders.add(Item().apply {
            this.itemID = "aaa111";
            this.itemPrice = 34.22
        })
        purchaseOrder.itemsOrders.add(Item().apply {
            this.itemID = "bbb222";
            this.itemPrice = 2.89
        })
        // when
        val purchaseOrderXml = objectMapper.writeValueAsString(purchaseOrder)
        // then
        assertEquals("""
            <PurchaseOrder>
              <ItemOrders>
                <Item>
                  <itemID>aaa111</itemID>
                  <itemPrice>34.22</itemPrice>
                </Item>
                <Item>
                  <itemID>bbb222</itemID>
                  <itemPrice>2.89</itemPrice>
                </Item>
              </ItemOrders>
            </PurchaseOrder>
            
        """.trimIndent(),purchaseOrderXml)
    }





}