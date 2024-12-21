# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn ru.mirea.bookingconferencerooms.coreui.theme.ThemeKt
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.AuthFeature
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.AuthFeatureDependencies
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction$Back
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction$OpenAuth
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction$OpenMain
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
-dontwarn ru.mirea.bookingconferencerooms.featureauth.api.models.AuthNavActionHandler
-dontwarn ru.mirea.bookingconferencerooms.featureauth.impl.api.AuthFeatureFactory
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeature
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureDependencies
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction$Auth
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction$Back
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction$Details
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction$Profile
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction
-dontwarn ru.mirea.bookingconferencerooms.featurebooking.impl.api.BookingFeatureFactory