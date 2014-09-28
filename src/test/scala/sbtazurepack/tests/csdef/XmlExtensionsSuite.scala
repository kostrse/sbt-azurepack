package sbtazurepack.tests.csdef

import org.scalatest.FlatSpec
import sbtazurepack.csdef._

class XmlExtensionsSuite extends FlatSpec {

  "optionalAttribute" should "return attribute value if element has attribute" in {

    val element = <element attr1="value1"></element>

    assertResult(Some("value1")) {
      element.optionalAttribute("attr1")
    }
  }

  it should "return None if element has no attributes" in {

    val element = <element />

    assertResult(None) {
      element.optionalAttribute("attr1")
    }
  }

  it should "return None if element has no attribute with given name" in {

    val element = <element attr2="value2"></element>

    assertResult(None) {
      element.optionalAttribute("attr1")
    }
  }

  "requiredAttribute" should "return attribute value if element has attribute" in {

    val element = <element attr1="value1"></element>

    assertResult("value1") {
      element.requiredAttribute("attr1")
    }
  }

  it should "throw CsDefFormatException if element has no attributes" in {

    val element = <element />

    intercept[CsDefFormatException] {
      element.requiredAttribute("attr1")
    }
  }

  it should "throw CsDefFormatExceptione if element has no attribute with given name" in {

    val element = <element attr2="value2"></element>

    intercept[CsDefFormatException] {
      element.requiredAttribute("attr1")
    }
  }

  "childElements" should "return all child elements" in {

    val element =
      <parent>
        <child1 id="1" />
        <!-- xml comment -->
        <child1 id="2" />
        <child2 id="3" />
        <child1 id="4" />
        <child2 id="5" />
      </parent>

    // The test element also contains comment and whitespace
    assume(element.child.length > 5)

    val expectedChildren = List(
        <child1 id="1" />,
        <child1 id="2" />,
        <child2 id="3" />,
        <child1 id="4" />,
        <child2 id="5" />
    )

    assertResult(expectedChildren) {
      element.childElements
    }
  }

  it should "return empty list if element has no child elements" in {

    val element = <parent/>

    assertResult(Nil) {
      element.childElements
    }
  }

  "childElements(name: String)" should "return all child elements with given name" in {

    val element =
      <parent>
        <child1 id="1" />
        <child1 id="2" />
        <child2 id="3" />
        <child1 id="4" />
        <child2 id="5" />
      </parent>

    val expectedChildren = List(
        <child1 id="1" />,
        <child1 id="2" />,
        <child1 id="4" />
    )

    assertResult(expectedChildren) {
      element.childElements("child1")
    }
  }

  it should "return empty list if element has no child elements" in {

    val element = <parent/>

    assertResult(Nil) {
      element.childElements("child1")
    }
  }

  it should "return empty list if element has no child elements with given name" in {

    val element =
      <parent>
        <child2 id="3" />
        <child2 id="5" />
      </parent>

    assertResult(Nil) {
      element.childElements("child1")
    }
  }
}
