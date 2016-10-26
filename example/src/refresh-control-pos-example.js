import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  Image,
  ScrollView,
  Platform,
  TouchableOpacity,
  RefreshControl
} from 'react-native';
import Invoke from 'react-native-invoke';

export default class RefreshControlPosExample extends Component {
  render() {
    return (
      <View style={styles.container}>
        <ScrollView style={{flex: 1}} refreshControl={
          <RefreshControl refreshing={true} ref='refresh' testID="123456"/>
        }>
          <Image style={{width: 320, height: 560}} source={{uri: 'http://i.imgur.com/Q6PCl4B.jpg'}} />
        </ScrollView>
        <View style={{flex: 1, justifyContent: 'center'}}>
          <TouchableOpacity onPress={this.onButtonPress.bind(this)}>
            <Text style={{color: 'blue', marginVertical: 20}}>Move Refresh Control</Text>
          </TouchableOpacity>
        </View>
      </View>
    );
  }
  
  async onButtonPress() {
    console.log("pressed");
    if (Platform.OS === 'ios') {
      //ObjC:  CGRect frame = componentView.frame;
      const _rctRefreshControl = Invoke.React.view(this.refs['refresh']);
      const _getRefreshFrame = Invoke.call(_rctRefreshControl, 'frame');
      let {x, y, width, height} = await Invoke.execute(_getRefreshFrame);
      y += 10;
      //ObjC:  [componentView setFrame:frame];
      const _setRefreshFrame = Invoke.call(_rctRefreshControl, 'setFrame:', Invoke.IOS.CGRect({x, y, width, height}));
      await Invoke.execute(_setRefreshFrame);
    } else {
      // reactSwipeRefreshLayout.setRefreshing(false);
      const swipeRefreshLayout = Invoke.React.view(this.refs['refresh']);
      const setRefreshing = Invoke.call(swipeRefreshLayout, 'setRefreshing', {type: "Boolean", value: false});
      await Invoke.execute(setRefreshing);
    }
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 20,
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
});
