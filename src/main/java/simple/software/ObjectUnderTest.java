package simple.software;

public class ObjectUnderTest {
    ExternalDBMock mockObject;

    public ObjectUnderTest(ExternalDBMock mockObject) {
        this.mockObject = mockObject;
    }

    public int getTotalValues() {
        return mockObject.getValue("abc") + mockObject.getValue("def") + mockObject.getValue("xyz");
    }
}
