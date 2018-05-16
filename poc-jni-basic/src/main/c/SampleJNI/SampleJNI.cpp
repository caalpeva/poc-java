#include "NativeMethods.h"
#include <stdlib.h>
#include <string.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

int isPrime(int n);

JNIEXPORT void JNICALL Java_NativeMethods_greet(JNIEnv *env, jclass jClass) {
	printf("In C, Hello World!\n");
	fflush(stdout);
	return;
}

JNIEXPORT jlong JNICALL Java_NativeMethods_sum(JNIEnv * env, jobject obj, jint m, jlong n) {
	printf("In C, compute %d + %ld\n", m, (long) n);
	fflush(stdout);
	jlong result = m + n ;
	return result;
}

JNIEXPORT jstring JNICALL Java_NativeMethods_reverse(JNIEnv * env, jobject obj, jstring s) {
	const char* inString = env->GetStringUTFChars(s, NULL);
	if (NULL == inString) return NULL;

	printf("In C, reverse %s\n", inString);
	fflush(stdout);

	int len = env->GetStringUTFLength(s);
	char* outString = (char*) malloc(strlen(inString) + 1);
	for (int i = 0; i < len; i++) {
		outString[i] = inString[len-i-1];
	} // for
	outString[len] = '\0';

	return env->NewStringUTF(outString);
}

JNIEXPORT jintArray JNICALL Java_NativeMethods_getPrimeNumbers(JNIEnv * env, jobject object, jint total) {
	jint * iArray = (jint*)malloc(total * sizeof(jint));
	int currentNumber = 1;
	for (int i = 0; i < total; ) {
		if (isPrime(currentNumber) == 1) {
			iArray[i++] = currentNumber;
		}
		currentNumber++;
	} // for

	jintArray resultArray = env->NewIntArray(total);
	env->SetIntArrayRegion(resultArray, 0, total, iArray);
	return resultArray;
}


JNIEXPORT jint JNICALL Java_NativeMethods_sumAndReverse(JNIEnv *env, jobject obj, jintArray intArr) {
	jboolean isCopy;
	jint * iarray = env->GetIntArrayElements(intArr, &isCopy);
	jsize len = env->GetArrayLength(intArr);

	jint count = 0;

	for (int k = 0; k < len / 2; k++) {
		jint temp = iarray[k];
		iarray[k] = iarray[len - k - 1];
		iarray[len - k - 1] = temp;
		count += (temp + iarray[k]);
	} // for

	if (isCopy == JNI_TRUE) {
		env->ReleaseIntArrayElements(intArr, iarray, 0);
	}

	return count;
}

JNIEXPORT void JNICALL Java_NativeMethods_setInstanceValue(JNIEnv *env, jobject thisObj, jobject obj, jint value) {
	printf("In C, change an instance variable\n");
	fflush(stdout);
	jclass clazz = env->GetObjectClass(obj);
	jfieldID fieldId = env->GetFieldID(clazz, "value", "I");
	env->SetIntField(obj, fieldId, value);
}

JNIEXPORT void JNICALL Java_NativeMethods_setClassValue(JNIEnv * env, jobject thisObj, jclass clazz, jdouble value) {
	printf("In C, change a class variable\n");
	fflush(stdout);
	jfieldID fieldId = env->GetStaticFieldID(clazz, "staticValue", "D");
	env->SetStaticDoubleField(clazz, fieldId, value);
}

JNIEXPORT void JNICALL Java_NativeMethods_compute__LSomeMethods_2(JNIEnv * env, jobject thisObj, jobject obj) {
	jclass clazz = env->GetObjectClass(obj);
	jmethodID methodId = env->GetMethodID(clazz, "multiply", "(ID)D");
	printf("In C, calling to Java function...\n");
	fflush(stdout);
	double value = env->CallDoubleMethod(obj, methodId, 4, 8.8);
	printf("In C, the result obtained is %.2f\n", value);
	fflush(stdout);
}

JNIEXPORT void JNICALL Java_NativeMethods_compute__Ljava_lang_Class_2(JNIEnv * env, jobject thisObj, jclass clazz) {
	jmethodID methodId = env->GetStaticMethodID(clazz, "onMessageReceived", "(Ljava/lang/String;)V");
	jstring text = env->NewStringUTF("Computation completed!");
	env->CallStaticVoidMethod(clazz, methodId, text);
}

int isPrime(int n) {
	for (int i = 2; i < n; i++) {
		if (n % i == 0)
			return 0;
	}
	return 1;
}