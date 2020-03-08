# react-native-recycler-list

## Getting started

`$ npm install react-native-recycler-list --save`

### Mostly automatic installation

`$ react-native link react-native-recycler-list`

## Usage
```javascript
import React, {Component} from 'react';
import {View, StyleSheet} from 'react-native';
import RecyclerList from 'react-native-recycler-list';

// Defining sample data , get this from your api as array of objects
const data = [
  {
    "id": "1",
    "createdAt": "2020-02-16T12:42:34.396Z",
    "name": "Dax Wolf",
    "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/mbilalsiddique1/128.jpg",
    "email": "first1@mail.com",
    "imageUrl": "https://unsplash.it/400?image=1"
  },
  {
    "id": "2",
    "createdAt": "2020-02-17T02:55:32.618Z",
    "name": "Henri Hilll",
    "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/louis_currie/128.jpg",
    "email": "first2@mail.com",
    "imageUrl": "https://unsplash.it/400?image=2"
  }
 ];
  
export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <RecyclerList
          style={styles.container}
          name="Ashutosh"
          src={data}
          onRefresh={event => {
            console.log('event : ', event.nativeEvent);
            alert('OnRefresh');
          }}
          onClick={event => {
            console.log('event : ', event.nativeEvent);
            alert('Data Clicked : ');
          }}
          onLongClick={event => {
            console.log('ON LONG CLICK CALLCED : ', event.nativeEvent);
            alert('Loing click');
          }}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: '100%',
  },
});

```
