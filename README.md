# FirstTalk_app
First Talk an an App which which bridges the communication gap between Deaf/Mute people whith normal people.

# Project Members:
- Ananya Sathyanarayanan
- Komal Bhagchandani
- Disha Sanghavi
- Neha Suva 

# Getting Started
To perform detection for ASL run:
python3 Detection/ASL_Detection/detection.py

To perform detection for ISL (in Hindi) run:
python3 Detection/Hindi/hindi_detection.py

To perform detection for ISL (in Gujarati) run:
python3 Detection/Gujarati/gujarati_detection.py

To start App:
Use Android Studio


# Introduction
This app bridges the gap between Deaf/Mute people and common people. It allows Deaf/Mute people to have regular conversations with common people on day to day basis, using our live detection system.

Due to the disabilities faced by these challenged people, they find it difficult to have regular conversations with common persons on a day-to-day basis. This app aims to bridge this gap. Using the live detection technology, these challenged people can talk with the common people. When these challenged people perform their sign language, the app recognizes their sign language and outputs them in text format. This app also has a learning section where common users can take up courses to learn sign language.

# Features
- Our app mainly uses a detection technology, through which the Deaf/Mute people act their sign language & the app outputs the text they are trying to communicate.
- Apart from detection technology and its uses, the app also contains a learning section where anyone can take up free courses to learn the language from scratch.
- Sign Language Detection can be performed in American Sign Language (ASL), Indian Sign Language (ISL). In ISL, we have encorporated Hindi & Gujarati Language.

# Technology Used
- TensorFlow
- OpenCV
- Mediapipe
- Android Studio
- Material Design
- Firebase
- TensorBoard

# Challenges Faced
- Integrating the machine model with the app functioning on Android studio.
Our machine model created a .h5 Keras file but it was not functioning with Android studio code. Later, on converting .h5 file into .tflite file, the code worked.

# Machine Model
- It is in Python Language
- Artificial Neural Network (ANN)
- It is a linear model.
- Problem Type used in Classification
- Overall Accuracy Rate is 0.96
- Overall Error Rate is is 0.12


