# ☀️ Android Weather App

The **AndroidWeatherApp** is a simple Android application that provides real-time weather
information for the user's location. It allows users to view the current weather conditions, check
the forecast,and search for weather updates of different cities.
Additionally, it's offers an **app widget** that displays the current weather on the home screen for
quick view.The widgets updates at a predefined interval, adhering to a predetermined refresh rate.

## 🌉 Features

- 🌈Current Weather: Get up-to-date weather data for the user's current location.
- 🌧️ Weather Forecast: View the weather forecast to plan ahead.
- 🏙️ City Search: Search for weather information in different cities.
- 📺 Home Screen Widget: Add a widget to the home screen to see the current weather at a glance.

## 🖼️ Screenshots

These are some of the screen shots of the App

<div align="center">
  <img width="24%" src="screenshots\current_weather_screen.png" />
  <img width="24%" src="screenshots\forecast_screen.png" />
  <img width="24%" src="screenshots\search_screen.png" />
  <img width="24%" src="screenshots\saved_cities.png" />
</div>

Screenshot for **widgets**

<div align="center">
  <img width="24%" src="screenshots\widget_preview.png" />
  <img width="24%" src="screenshots\actual_widget.png" />

</div>

## 🤙Contributing

If you would like to contribute to [Reminders app](https://github.com/tuuhin/Reminders), follow
these steps:

1. Fork the repository on Github.
2. Create a new branch with your changes.
3. Commit your changes and push your branch to your fork.
4. Submit a pull request to the main repository.

To ensure accurate weather data, please sign up at [weatherapi.com](https://www.weatherapi.com/) and
obtain your unique API key.
After that create a new property in `local.properties` file

```properties
API_KEY=<your_api_key>
```
You are set to go!!

## 🔚 Conclusion

This app is mainly created to try out
the [Jetpack Glance library](https://developer.android.com/jetpack/compose/glance/) ,was great for
creating home screen app widgets and to make another weather app in android and using compose
Previously I had already made an [weather app](https://github.com/tuuhin/weather_app),but this new
version looks more polished and clean.

I have learned a lot in making this, hopefully you like it.I am welcome any feedback or suggestions
for improvement.
