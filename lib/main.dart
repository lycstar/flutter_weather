import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:weather/home.dart';

void main() {
  SystemUiOverlayStyle systemUiOverlayStyle =
      SystemUiOverlayStyle(statusBarColor: Colors.transparent);
  SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '天气',
      theme: ThemeData.light()
          .copyWith(appBarTheme: AppBarTheme(color: Colors.transparent)),
      home: HomePage(),
    );
  }
}
