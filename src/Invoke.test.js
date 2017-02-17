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

  it('function invocation call, no params', () => {
    const func = jest.fn();
    func.mockReturnValue('a string');
    const testCall = Invoke.call(func , 'length');

    const expectedResult = {
      args: [],
      method: "length",
      target: {
        type: "Invocation",
        value: "a string"
      }
    };

    expect(testCall()).toEqual(expectedResult);
  });
});
