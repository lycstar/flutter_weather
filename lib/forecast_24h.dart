import 'dart:ui';

import 'package:flutter/material.dart';

class Forecast24HPage extends StatefulWidget {
  @override
  _Forecast24HPageState createState() => _Forecast24HPageState();
}

class _Forecast24HPageState extends State<Forecast24HPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height -
          kToolbarHeight -
          MediaQueryData.fromWindow(window).padding.top,
      color: Colors.transparent,
    );
  }
}
