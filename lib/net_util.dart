import 'package:dio/dio.dart';

class NetUtil {
  static void getWeatherData(String province, String city) async {
    var dio = Dio();
    Response response =
        await dio.get('https://wis.qq.com/weather/common', queryParameters: {
      "source": "xw",
      "weather_type":
          "air|observe|rise|forecast_1h|forecast_24h|alarm|index|limit",
      "province": province,
      "city": city,
    });
    print(response.data);
  }
}
