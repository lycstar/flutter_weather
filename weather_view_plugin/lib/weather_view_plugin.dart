import 'dart:async';

import 'package:flutter/services.dart';

class WeatherViewPlugin {
  static const MethodChannel _channel =
      const MethodChannel('weather_view_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
