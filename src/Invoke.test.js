const Invoke = require('./Invoke');

describe('Invoke.call', () => {
  it('static class call', () => {
    const testCall = Invoke.call('TestClass', 'testFunction', {type: "Boolean", value: false});

    const expectedResult = {
      args:
      [
        {
          type: "Boolean",
          value: false
        }
      ],
      method: "testFunction",
      target: "TestClass"
    };

    expect(testCall()).toEqual(expectedResult);
  });
});
