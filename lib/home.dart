import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Stack(
//      decoration: BoxDecoration(
//          image: DecorationImage(
////        image: AssetImage('images/bkg_cloudy_night.png'),
//        fit: BoxFit.scaleDown,
//      )),
      children: <Widget>[
        AndroidView(viewType: "plugins.flutter.io/cloudyview"),
        _weatherContent
      ],
    );
  }

  get _weatherContent => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: _appbar,
      );

  get _appbar => AppBar(
        elevation: 0.0,
        centerTitle: true,
        leading: IconButton(icon: Icon(Icons.location_on), onPressed: () {}),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.location_city), onPressed: () {}),
          IconButton(icon: Icon(Icons.more_vert), onPressed: () {})
        ],
      );
}
