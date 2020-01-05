import 'package:flutter/material.dart';
import 'package:weather/forecast_24h.dart';
import 'package:weather/net_util.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final city = "顺庆";

  @override
  void initState() {
    NetUtil.getWeatherData("四川", "南充");
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
//      decoration: BoxDecoration(
//          image: DecorationImage(
////        image: AssetImage('images/bkg_cloudy_night.png'),
//        fit: BoxFit.scaleDown,
//      )),
      children: <Widget>[AndroidView(viewType: "cloudy"), _scaffold],
    );
  }

  get _scaffold => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: _appbar,
        body: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            Forecast24HPage(),
          ],
        ),
      );

  get _appbar => AppBar(
        elevation: 0.0,
        centerTitle: true,
        title: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Icon(Icons.navigation, size: 16),
            SizedBox(width: 4),
            Text(city, style: TextStyle(fontSize: 16))
          ],
        ),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.location_city), onPressed: () {}),
          IconButton(icon: Icon(Icons.more_vert), onPressed: () {})
        ],
      );
}
