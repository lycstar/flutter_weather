import 'package:flutter/material.dart';
import 'package:weather/shape/cloud.dart';
import 'package:weather/shape/cloud_painter.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Container(
//      decoration: BoxDecoration(
//          image: DecorationImage(
////        image: AssetImage('images/bkg_cloudy_night.png'),
//        fit: BoxFit.scaleDown,
//      )),
      child: _weatherContent,
    );
  }

  get _weatherContent => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          elevation: 0.0,
          leading: IconButton(icon: Icon(Icons.location_on), onPressed: () {}),
        ),
        body: CustomPaint(
          painter: Cloud(100, 200, 30, 50),
        ),
      );
}
