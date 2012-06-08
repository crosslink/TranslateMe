from google.appengine.ext import db

class Translation(db.Model):
    key_id = db.IntegerProperty()
    lang = db.StringProperty()
    translation = db.StringProperty()
    last_update = db.DateProperty()
    locked = db.BooleanProperty()
