package simple.software;

public class ObjectUnderTest {
    MockScoreDB mockObject;

    public ObjectUnderTest(MockScoreDB mockObject) {
        this.mockObject = mockObject;
    }

    public int getTotalValues() {
        return mockObject.getValue("abc") + mockObject.getValue("def") + mockObject.getValue("xyz");
    }
}
