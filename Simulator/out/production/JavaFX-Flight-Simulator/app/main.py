import speech_recognition as sr

r = sr.Recognizer()

with sr.Microphone() as source:
    command_audio = r.listen(source)

command_string = ""

try:
    command_string = r.recognize_google(command_audio)
    print(command_string)
except LookupError:
    print("Couldn't understand you, bro.")
