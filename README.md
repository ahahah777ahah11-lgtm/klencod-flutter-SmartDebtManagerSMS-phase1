# SMS المحاسب الذكي — Smart Debt Manager SMS

المرحلة 1: الأساس (Gradle + Room + Compose RTL).

## ما تحتويه هذه المرحلة
- مشروع Kotlin / Jetpack Compose (minSdk 33)، معرف التطبيق `com.smartdebt.sms`.
- قاعدة Room: العملاء، الحركات، دفعات الاستيراد، سجل التدقيق.
- المبالغ تُخزَّن كأعداد صحيحة بوحدات العملة الصغرى (`Money`).
- شاشتان: لوحة التحكم (أرقام من قاعدة البيانات) والعملاء (إضافة + بحث).
- ألوان الهوية من المواصفات، ووضع فاتح/داكن، واتجاه RTL إجباري.

## البناء
1. افتح المجلد في Android Studio (Koala أو أحدث) مع JDK 17.
2. اترك Android Studio يزامن Gradle وينشئ Gradle Wrapper، أو نفّذ مرة واحدة: `gradle wrapper --gradle-version 8.9`
3. `./gradlew assembleDebug` ثم الملف في `app/build/outputs/apk/debug/`.
4. الاختبارات: `./gradlew test`

## ملاحظات
- لم يُبنَ هذا المشروع ولم يُختبر على جهاز بعد (بيئة الإنشاء بلا إنترنت).
- خط Cairo غير مضمَّن؛ يستخدم النظام خط الجهاز العربي حاليًا.
- أنواع الحركات (`TransactionType`) معرّفة، لكن قواعد الأرصدة تأتي في المرحلة 2.
