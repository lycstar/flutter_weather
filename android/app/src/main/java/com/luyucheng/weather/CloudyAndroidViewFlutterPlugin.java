package com.luyucheng.weather;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry;

public class CloudyAndroidViewFlutterPlugin implements FlutterPlugin {

    public static void registerWith(PluginRegistry.Registrar registrar) {
        OrientationSensorUtil.requestSensor(registrar.context());
        registrar
                .platformViewRegistry()
                .registerViewFactory(
                        "cloudy",
                        new CloudyAndroidViewFactory(registrar.messenger(), registrar.view()));
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        OrientationSensorUtil.requestSensor(binding.getApplicationContext());
        BinaryMessenger messenger = binding.getFlutterEngine().getDartExecutor();
        binding
                .getFlutterEngine()
                .getPlatformViewsController()
                .getRegistry()
                .registerViewFactory(
                        "cloudy", new CloudyAndroidViewFactory(messenger, /*containerView=*/ null));
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
        OrientationSensorUtil.releaseSensor();
    }
}